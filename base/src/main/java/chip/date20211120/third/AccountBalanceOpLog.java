package chip.date20211120.third;

import java.math.BigDecimal;

public class AccountBalanceOpLog {

    private Long orderId;

    private Long accountId;

    private BigDecimal amount;

    private AccountBalanceOpLogStatus status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountBalanceOpLogStatus getStatus() {
        return status;
    }

    public void setStatus(AccountBalanceOpLogStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountBalanceOpLog{" +
                "orderId=" + orderId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
