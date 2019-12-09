package proxy.sta;

public interface PayService {
    /**
     * 假设为减去XX账户的余额
     * @param subtrahend 减数
     * @return true: 成功, false: 失败
     */
    boolean subtract(int subtrahend);
}
