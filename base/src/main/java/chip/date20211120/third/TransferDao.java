package chip.date20211120.third;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟存储
 */
public class TransferDao {


    private final Map<Long, TransferOrder> data = new HashMap<>();

    public void save(TransferOrder order) {
        data.put(order.getId(), order);
    }

    public TransferOrder query(Long id) {
        return data.get(id);
    }

}
