package proxy.dynamic;

/**
 * 订单服务
 */
public interface OrderService {
    /**
     * 假设为确认订单
     *
     * @param number XX商品数量
     * @param money  需要支付总额
     */
    void confirm(int number, int money);
}
