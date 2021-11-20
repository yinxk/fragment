package chip.date20211120.second;

public class ConsultResult {
    /**
     * 咨询结果是否可用
     */
    private boolean isEnable;
    /**
     * 错误码
     */
    private String errorCode;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}