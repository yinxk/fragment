package chip.date20211120.third;

import java.math.BigDecimal;

/**
 * 这个还没有用到, 服务不可用时降级
 */
public class TransferServiceCallback implements TransferService {

    @Override
    public TransferOrderResult createOrder(long from, long to, BigDecimal amount) {
        // 服务不可用
        return new TransferOrderResult(null, "");
    }

    @Override
    public TransferResult transfer(long orderId) {
        // 服务不可用
        return new TransferResult(false, "");
    }

}
