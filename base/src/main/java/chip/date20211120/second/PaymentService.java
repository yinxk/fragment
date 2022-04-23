package chip.date20211120.second;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(6, 10, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(6), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final Integer TIME_OUT_MILLISECONDS = 200;

    private PaymentRemoteSerivce paymentRemoteService = new PaymentRemoteSerivceImpl();


    /**
     * 过滤不可用支付方式类型
     *
     * @param paymentTypeList 原始支付方式类型列表
     * @return 可用支付方式类型列表
     */
    public List<String> filterDisablePayment(List<String> paymentTypeList) {
        if (paymentTypeList == null) {
            return Collections.emptyList();
        }
        int size = paymentTypeList.size();
        List<Future<ConsultResult>> taskList = new ArrayList<>(size);
        for (String paymentType : paymentTypeList) {
            Future<ConsultResult> task = EXECUTOR_SERVICE.submit(() -> {
                try {
                    return paymentRemoteService.isEnabled(paymentType);
                } catch (Exception e) {
                    LOGGER.warn("支付方式 [{}] 咨询失败 [{}]", paymentType, e.getMessage());
                }
                return null;
            });
            taskList.add(task);
        }

        List<String> canUsedPaymentTypeList = new ArrayList<>();
        boolean[] hasRes = new boolean[size];
        long start = System.currentTimeMillis();
        boolean isAllHasRes;
        while (true) {
            isAllHasRes = false;
            for (int i = 0; i < size; i++) {
                Future<ConsultResult> task = taskList.get(i);
                Boolean res = null;
                String paymentType = paymentTypeList.get(i);
                if (task.isDone() && !hasRes[i]) {
                    try {
                        res = Optional.ofNullable(task.get()).map(ConsultResult::isEnable).orElse(null);
                        hasRes[i] = true;
                    } catch (ExecutionException | InterruptedException e) {
                        LOGGER.warn("支付方式 [{}] 咨询失败 [{}]", paymentType, e.getMessage());
                    }
                } else {
                    if (System.currentTimeMillis() - start > TIME_OUT_MILLISECONDS) {
                        task.cancel(true);
                        hasRes[i] = true;
                    }
                }
                if (res != null && res) {
                    canUsedPaymentTypeList.add(paymentType);
                }
                if (i == 0) {
                    isAllHasRes = hasRes[i];
                }
                isAllHasRes = isAllHasRes && hasRes[i];
            }
            if (isAllHasRes) {
                break;
            }
        }

        return canUsedPaymentTypeList;
    }


    public static void main(String[] args) {


        PaymentService paymentService = new PaymentService();

        long start = System.currentTimeMillis();
        int size = 10;
        List<String> paymentTypeList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            paymentTypeList.add((i + 1) + "");
        }
        List<String> strings = paymentService.filterDisablePayment(paymentTypeList);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println("使用时间: " + (System.currentTimeMillis() - start));

    }

}
