package chip.date20211120.third;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟存储
 */
public class AccountDao {

    private final Map<Long, Account> data = new LinkedHashMap<>();

    public AccountDao() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(new BigDecimal("1000"));
        data.put(account.getId(), account);
        account = new Account();
        account.setId(2L);
        account.setBalance(new BigDecimal("2000"));
        data.put(account.getId(), account);
    }

    public Account query(Long id) {
        return data.get(id);
    }

    public boolean updateBalance(Long id, BigDecimal amount) {
        Account account = query(id);
        BigDecimal newBalance = account.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            account.setBalance(newBalance);
            return true;
        }
        return false;
    }

    public List<Account> listAccounts() {
        return new ArrayList<>(data.values());
    }
}
