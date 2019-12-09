package proxy.dynamic.impl;

import proxy.dynamic.InventoryService;
import proxy.dynamic.OrderService;
import proxy.dynamic.PayService;

public class OrderServiceImpl implements OrderService {
    InventoryService inventoryService = new InventoryServiceImpl();
    PayService payService = new PayServiceImpl();

    @Override
    public void confirm(int number, int money) {
        payService.subtract(money);
        inventoryService.subtract(number);
        System.out.println("生成订单数据");
    }
}
