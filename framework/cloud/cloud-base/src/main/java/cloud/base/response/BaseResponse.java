package cloud.base.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
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
