package chip.date20211120.second;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentRemoteSerivceImpl implements PaymentRemoteSerivce {

    private AtomicInteger nums = new AtomicInteger();


    @Override
    public ConsultResult isEnabled(String paymentType) {
        ConsultResult consultResult = new ConsultResult();
        int num = nums.getAndIncrement();
        if (num % 3 == 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
        } else if (num % 7 == 0) {
            throw new RuntimeException("test");
        } else {
            consultResult.setEnable(true);
        }
        return consultResult;
    }
}
