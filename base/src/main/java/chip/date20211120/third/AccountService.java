package chip.date20211120.third;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 模拟远程服务
 */
public class AccountService {

    private final AccountDao accountDao = new AccountDao();

    private final AccountBalanceOpLogDao accountBalanceOpLogDao = new AccountBalanceOpLogDao();

    private boolean check0(Long accountId, BigDecimal amount) {
        Account account = accountDao.query(accountId);
        if (account == null) {
            return false;
        }
        BigDecimal sum = account.getBalance().add(amount);
        return sum.compareTo(BigDecimal.ZERO) >= 0;
    }

    public List<Account> listAccounts() {
        return accountDao.listAccounts();
    }

    public List<AccountBalanceOpLog> listAccountBalanceOpLog() {
        return accountBalanceOpLogDao.listAccountBalanceOpLog();
    }

    public AccountOperateResult check(Long accountId, BigDecimal amount) {
        return new AccountOperateResult(check0(accountId, amount), "");
    }

    /**
     * 本地事务
     */
    public AccountOperateResult tryUpdateBalance(Long orderId, Long accountId, BigDecimal amount) {
        try {
            if (check0(accountId, amount)) {
                accountBalanceOpLogDao.save(orderId, accountId, amount);
            } else {
                return new AccountOperateResult(false, "");
            }
        } catch (Exception e) {
            return new AccountOperateResult(false, "");
        }
        return new AccountOperateResult(true, "");
    }

    public AccountOperateResult commitBalance(Long orderId, Long accountId, BigDecimal amount) {
        boolean update = accountBalanceOpLogDao.update(orderId, accountId,
                Collections.singletonList(AccountBalanceOpLogStatus.INTI), AccountBalanceOpLogStatus.SUCCESS);
        if (update) {
            boolean updateBalance = accountDao.updateBalance(accountId, amount);
            if (updateBalance) {
                return new AccountOperateResult(true, null);
            } else {
                accountBalanceOpLogDao.update(orderId, accountId,
                        Collections.singletonList(AccountBalanceOpLogStatus.SUCCESS), AccountBalanceOpLogStatus.INTI);
            }
        }
        return new AccountOperateResult(false, "");
    }

    public AccountOperateResult rollbackBalance(Long orderId, Long accountId, BigDecimal amount) {
        boolean update = accountBalanceOpLogDao.update(orderId, accountId,
                Arrays.asList(AccountBalanceOpLogStatus.SUCCESS, AccountBalanceOpLogStatus.ROLLBACK_FAIL),
                AccountBalanceOpLogStatus.ROLLBACK_SUCCESS);
        if (update) {
            boolean updateBalance = accountDao.updateBalance(accountId, amount.negate());
            if (updateBalance) {
                return new AccountOperateResult(updateBalance, null);
            } else {
                accountBalanceOpLogDao.update(orderId, accountId,
                        Collections.singletonList(AccountBalanceOpLogStatus.ROLLBACK_SUCCESS),
                        AccountBalanceOpLogStatus.ROLLBACK_FAIL);
            }
        }
        return new AccountOperateResult(false, "");
    }
}
