package chip.date20211120.third;

public class TransferOrderResult extends ErrorCode{
    /**
     * 订单ID
     */
    private Long id;

    public TransferOrderResult(Long id, String errorCode) {
        super(errorCode);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
