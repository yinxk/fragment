package chip.date20211120.third;

import java.math.BigDecimal;

public class TransferOrder {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 被转资金账户
     */
    private Long from;

    /**
     * 转入资金账户
     */
    private Long to;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态
     */
    private TransferOrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransferOrderStatus getStatus() {
        return status;
    }

    public void setStatus(TransferOrderStatus status) {
        this.status = status;
    }
}
