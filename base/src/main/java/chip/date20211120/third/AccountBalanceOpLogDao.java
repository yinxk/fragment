package chip.date20211120.third;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟存储
 */
public class AccountBalanceOpLogDao {


    private Map<String, AccountBalanceOpLog> data = new LinkedHashMap<>();

    public void save(Long orderId, Long accountId, BigDecimal amount) {
        String key = key(orderId, accountId);
        AccountBalanceOpLog accountBalanceOpLog = data.get(key);
        if (accountBalanceOpLog != null) {
            throw new RuntimeException("重复主键");
        }
        accountBalanceOpLog = new AccountBalanceOpLog();
        accountBalanceOpLog.setOrderId(orderId);
        accountBalanceOpLog.setAccountId(accountId);
        accountBalanceOpLog.setAmount(amount);
        accountBalanceOpLog.setStatus(AccountBalanceOpLogStatus.INTI);
        data.put(key, accountBalanceOpLog);
    }


    public boolean update(Long orderId, Long accountId, Collection<AccountBalanceOpLogStatus> lastStatusSet,
                          AccountBalanceOpLogStatus newStatus) {
        String key = key(orderId, accountId);
        AccountBalanceOpLog accountBalanceOpLog = data.get(key);
        if (accountBalanceOpLog == null) {
            return false;
        }
        if (lastStatusSet.contains(accountBalanceOpLog.getStatus())) {
            accountBalanceOpLog.setStatus(newStatus);
            return true;
        }
        return false;
    }

    public List<AccountBalanceOpLog> listAccountBalanceOpLog() {
        return new ArrayList<>(data.values());
    }

    private String key(Long orderId, Long accountId) {
        return orderId + "" + accountId;
    }

}
