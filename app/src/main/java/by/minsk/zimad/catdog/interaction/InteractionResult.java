package by.minsk.zimad.catdog.interaction;

public class InteractionResult<T> {
    private final boolean isSuccess;
    private final T data;
    private final Throwable error;

    public InteractionResult(boolean isSuccess, T data, Throwable error) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.error = error;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}
