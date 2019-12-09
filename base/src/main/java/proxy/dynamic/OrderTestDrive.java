package proxy.dynamic;

import proxy.dynamic.impl.OrderServiceImpl;

public class OrderTestDrive {
    public static void main(String[] args) {

        OrderService orderService = new OrderServiceImpl();
        OrderService orderServiceProxy = new OrderServiceImpl();

        orderServiceProxy.confirm(9, 90);
        System.out.println("======");
        orderServiceProxy.confirm(2, 10);

    }
}
