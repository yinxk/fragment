package chip.date20211120.third;

public class AccountOperateResult extends ErrorCode {

    private boolean success;

    public AccountOperateResult(boolean success,String errorCode) {
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
