package leetcode;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class A122BestTimeToBuyAndSellStockIi {

    public static void main(String[] args) {

        A122BestTimeToBuyAndSellStockIi obj = new A122BestTimeToBuyAndSellStockIi();
        System.out.println(obj.maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(obj.maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(obj.maxProfit(new int[]{7,6,4,3,1}));

    }

    /**
     * 自己在这里想了挺久的, 结果没有好的思路, 想了从后面遍历等, 核心想法没有想法, 不仅做法复杂, 而且结果还不对
     *
     *
     * 本题, 看了题解, 简单, 清晰
     * 股票买卖策略：
     * 单独交易日： 设今天价格 p1、明天价格 p2，则今天买入、明天卖出可赚取金额 p2 - p1（负值代表亏损）。
     * 连续上涨交易日： 设此上涨交易日股票价格分别为 p1, p2, ... , pn，则第一天买最后一天卖收益最大，即 p1 - p1；
     * <B>等价于每天都买卖 </B>，即 pn - p1=(p2 - p1)+(p3 - p2)+...+(pn - p(n-1))。
     * 连续下降交易日： 则不买卖收益最大，即不会亏钱。
     */
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
