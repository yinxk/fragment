package proxy.dynamic.impl;

import proxy.dynamic.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    private static int capacity = 10;
    @Override
    public void subtract(int subtrahend) {
        // 不考虑多线程问题, 溢出问题
        if (subtrahend < 0) {
            throw new RuntimeException("参数错误");
        }
        if (subtrahend > capacity) {
            throw new RuntimeException("库存不足");
        }
        capacity = capacity - subtrahend;
        System.out.println("更新库存, 当前库存: " + capacity + ", 出库数量: " + subtrahend);
    }
}
