package proxy.sta.impl;

import proxy.sta.OrderService;

public class OrderServiceProxyImpl implements OrderService {
    OrderService orderService;

    public OrderServiceProxyImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void confirm(int number, int money) {
        try {
            System.out.println("开始事务");
            orderService.confirm(number, money);
            System.out.println("提交事务");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("由于: " + e.getMessage() + ", 回滚事务");
        }
    }
}
