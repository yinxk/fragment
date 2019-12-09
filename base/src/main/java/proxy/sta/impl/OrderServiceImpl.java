package proxy.sta.impl;

import proxy.sta.InventoryService;
import proxy.sta.OrderService;
import proxy.sta.PayService;

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
