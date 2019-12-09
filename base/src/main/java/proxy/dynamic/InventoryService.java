package proxy.dynamic;

public interface InventoryService {

    /**
     * 假设为减去XX商品的库存数量
     * @param subtrahend 减数
     */
    void subtract(int subtrahend);
}
