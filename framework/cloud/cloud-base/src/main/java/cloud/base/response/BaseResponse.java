package cloud.base.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private static final long serialVersionUID = -3407739676896796852L;
    private String code;

    private String message;

    private T data;

    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(T data) {
        this("2000", "success", data);
    }


}
