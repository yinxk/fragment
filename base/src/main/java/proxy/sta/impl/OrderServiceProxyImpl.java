package proxy.sta.impl;

import proxy.sta.OrderService;

public class OrderServiceProxyImpl implements OrderService {
    OrderService orderService;

    public OrderServiceProxyImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public boolean confirm(int number, int money) {
        try {
            return orderService.confirm(number, money);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("由于: " + e.getMessage() + ", 回滚之前的操作");
        }
        return false;
    }
}
