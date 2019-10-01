package by.minsk.zimad.catdog.network;

public class BaseResponse<T> {

    private final String message;
    private final T data;

    public BaseResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccessful() {
        return message == null;
    }
}
