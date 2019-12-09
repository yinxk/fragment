package proxy.sta.impl;

import proxy.sta.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    private static int capacity = 10;
    @Override
    public boolean subtract(int subtrahend) {
        // 不考虑多线程问题, 溢出问题
        if (subtrahend < 0) {
            return false;
        }
        if (subtrahend > capacity) {
            throw new RuntimeException("库存不足");
        }
        capacity = capacity - subtrahend;
        System.out.println("更新库存, 当前库存: " + capacity + ", 出库数量: " + subtrahend);
        return true;
    }
}
