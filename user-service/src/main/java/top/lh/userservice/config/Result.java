package top.lh.userservice.config;

import lombok.Data;

@Data
public class Result<T> {
    private int errorCode;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setErrorCode(0);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setErrorCode(1);
        result.setMessage(message);
        return result;
    }

    // Getters and Setters
}
