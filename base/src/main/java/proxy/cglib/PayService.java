package proxy.cglib;

public interface PayService {
    /**
     * 假设为减去XX账户的余额
     * @param subtrahend 减数
     */
    void subtract(int subtrahend);
}
