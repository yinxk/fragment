package proxy.sta.impl;

import proxy.sta.PayService;

public class PayServiceImpl implements PayService {
    private static int balance = 100;

    @Override
    public void subtract(int subtrahend) {
        // 不考虑多线程问题, 溢出问题
        if (subtrahend < 0) {
            throw new RuntimeException("参数错误");
        }
        if (subtrahend > balance) {
            throw new RuntimeException("余额不足");
        }
        balance = balance - subtrahend;
        System.out.println("更新余额, 当前余额: " + balance + ", 消费金额: " + subtrahend);
    }
}
