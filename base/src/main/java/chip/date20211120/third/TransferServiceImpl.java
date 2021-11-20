package chip.date20211120.third;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferServiceImpl implements TransferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferServiceImpl.class);

    private static final Integer TIMEOUT_MILLISECONDS = 200;

    private final AccountService accountService;

    private final TransferDao transferDao = new TransferDao();

    private final DistributedLock distributedLock = new DistributedLock();

    private final DistributedIdService distributedIdService = new DistributedIdService();

    private final RetryService<AccountOperateResult, Boolean> retryService = new RetryService<>();

    public TransferServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public TransferOrderResult createOrder(long from, long to, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransferOrderResult(null, "");
        }
        TransferOrderResult check = checkAccount(from, amount.negate());
        if (check != null) {
            return check;
        }
        check = checkAccount(to, amount);
        if (check != null) {
            return check;
        }
        String key = key(from, to);
        try {
            if (distributedLock.lock(key, TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)) {
                TransferOrder order = new TransferOrder();
                order.setId(distributedIdService.nextId());
                order.setFrom(from);
                order.setTo(to);
                order.setAmount(amount);
                order.setStatus(TransferOrderStatus.INIT);
                transferDao.save(order);
                return new TransferOrderResult(order.getId(), null);
            }
        } finally {
            distributedLock.unlock(key);
        }
        return new TransferOrderResult(null, "");
    }


    private TransferOrderResult checkAccount(long from, BigDecimal amount) {
        AccountOperateResult check = accountService.check(from, amount);
        if (!isOpSuccess(check, Boolean.FALSE)) {
            return new TransferOrderResult(null,
                    Optional.ofNullable(check).map(AccountOperateResult::getErrorCode).orElse(""));
        }
        return null;
    }

    @Override
    public TransferResult transfer(final long orderId) {
        TransferOrder order = transferDao.query(orderId);
        if (order == null) {
            return new TransferResult(false, "");
        }
        long fromAccountId = order.getFrom();
        long toAccountId = order.getTo();
        BigDecimal amount = order.getAmount();

        AccountOperateResult resultFrom = trySubmit(orderId, fromAccountId, amount.negate());
        AccountOperateResult resultTo = trySubmit(orderId, toAccountId, amount);
        if (isOpAllSuccess(resultFrom, resultTo)) {
            try {
                resultFrom = commit(orderId, fromAccountId, amount.negate());
                resultTo = commit(orderId, toAccountId, amount);
                if (isOpAllSuccess(resultFrom, resultTo)) {
                    return new TransferResult(true, null);
                } else {
                    rollback(orderId, fromAccountId, amount);
                    rollback(orderId, toAccountId, amount.negate());
                }
            } catch (Exception e) {
                rollback(orderId, fromAccountId, amount);
                rollback(orderId, toAccountId, amount.negate());
            }
        }
        return new TransferResult(false, null);
    }

    private AccountOperateResult trySubmit(Long orderId, Long accountId, BigDecimal amount) {
        return accountService.tryUpdateBalance(orderId, accountId, amount);
    }

    private AccountOperateResult commit(Long orderId, Long accountId, BigDecimal amount) {
        return accountService.commitBalance(orderId, accountId, amount);
    }

    private void rollback(Long orderId, Long accountId, BigDecimal amount) {
        AccountOperateResult result = accountService.rollbackBalance(orderId, accountId, amount);
        if (!result.isSuccess()) {
            retryService.execute(() -> accountService.rollbackBalance(orderId,
                    accountId, amount.negate()), AccountOperateResult::isSuccess);
        }
    }

    private String key(long from, long to) {
        return from + "" + to;
    }

    private boolean isOpAllSuccess(AccountOperateResult... results) {
        for (AccountOperateResult result : results) {
            if (!isOpSuccess(result, false)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOpSuccess(AccountOperateResult accountOperateResult, boolean defaultValue) {
        return Optional.ofNullable(accountOperateResult).map(AccountOperateResult::isSuccess).orElse(defaultValue);
    }
}
