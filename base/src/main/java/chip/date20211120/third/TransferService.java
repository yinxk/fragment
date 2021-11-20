package chip.date20211120.third;

import java.math.BigDecimal;

public interface TransferService {

    /**
     * 创建订单
     *
     * @param from   被转资金账户
     * @param to     转入资金账户
     * @param amount 金额
     * @return 创建结果
     */
    TransferOrderResult createOrder(long from, long to, BigDecimal amount);


    /**
     * 转账
     *
     * @param orderId 订单ID
     */
    TransferResult transfer(long orderId);


}
