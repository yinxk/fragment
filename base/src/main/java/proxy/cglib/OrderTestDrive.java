package proxy.cglib;


public class OrderTestDrive {
    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        OrderService orderServiceProxy = (OrderService) new ProxyFactory(orderService).getProxyInstance();

        orderServiceProxy.confirm(9, 90);
        System.out.println("======");
        orderServiceProxy.confirm(2, 10);

    }
}
