package chip.date20211120.third;

public class TransferResult extends ErrorCode {
    /**
     * 转账是否成功
     */
    private boolean success;

    public TransferResult(boolean success, String errorCode) {
        super(errorCode);
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}