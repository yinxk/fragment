package proxy.dynamic.impl;

import proxy.dynamic.InventoryService;
import proxy.dynamic.OrderService;
import proxy.dynamic.PayService;

public class OrderServiceImpl implements OrderService {
    InventoryService inventoryService = new InventoryServiceImpl();
    PayService payService = new PayServiceImpl();

    @Override
    public boolean confirm(int number, int money) {
        boolean updateBalance = payService.subtract(money);
        boolean updateCapacity = inventoryService.subtract(number);

        if (updateBalance && updateCapacity) {
            System.out.println("生成订单数据");
            return true;
        }
        return false;
    }
}
