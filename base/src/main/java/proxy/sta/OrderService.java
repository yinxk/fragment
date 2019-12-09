package proxy.sta;

/**
 * 订单服务
 */
public interface OrderService {
    /**
     * 假设为确认订单
     *
     * @param number XX商品数量
     * @param money  需要支付总额
     * @return true: 确认生成订单成功, false: 确认生成订单失败
     */
    boolean confirm(int number, int money);
}
