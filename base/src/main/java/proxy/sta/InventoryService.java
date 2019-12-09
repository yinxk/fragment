package proxy.sta;

public interface InventoryService {

    /**
     * 假设为减去XX商品的库存数量
     * @param subtrahend 减数
     * @return true: 成功, false: 失败
     */
    boolean subtract(int subtrahend);
}
