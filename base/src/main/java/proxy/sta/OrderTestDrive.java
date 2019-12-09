package proxy.sta;

import proxy.sta.impl.OrderServiceImpl;
import proxy.sta.impl.OrderServiceProxyImpl;

public class OrderTestDrive {
    public static void main(String[] args) {

        OrderService orderService = new OrderServiceImpl();
        OrderService orderServiceProxy = new OrderServiceProxyImpl(orderService);

        orderServiceProxy.confirm(9, 90);
        System.out.println("======");
        orderServiceProxy.confirm(2, 10);

    }
}
