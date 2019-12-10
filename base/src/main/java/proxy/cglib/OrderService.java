package proxy.cglib;

import proxy.dynamic.InventoryService;
import proxy.dynamic.PayService;
import proxy.dynamic.impl.InventoryServiceImpl;
import proxy.dynamic.impl.PayServiceImpl;

public class OrderService {

    InventoryService inventoryService = new InventoryServiceImpl();
    PayService payService = new PayServiceImpl();

    public void confirm(int number, int money) {
        payService.subtract(money);
        inventoryService.subtract(number);
        System.out.println("生成订单数据");
    }
}
