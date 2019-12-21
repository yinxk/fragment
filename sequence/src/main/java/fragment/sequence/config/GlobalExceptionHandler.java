package fragment.sequence.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fragment.sequence.exception.SequenceException;
import fragment.sequence.exception.SequenceNotFoundException;
import fragment.sequence.exception.SequenceOverflowException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SequenceNotFoundException.class)
    public String sequenceNotFound(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "不存在的序列";
    }
    @ExceptionHandler(SequenceOverflowException.class)
    public String sequenceOverflow(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "序列值超出最大值";
    }
    @ExceptionHandler(SequenceException.class)
    public String sequenceException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "获取序列值异常";
    }
    @ExceptionHandler(RuntimeException.class)
    public String runtimeException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return "服务器异常";
    }

}
