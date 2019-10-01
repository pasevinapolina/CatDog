package by.minsk.zimad.catdog.presentation.common;

public interface MvpView<S> {
    void render(S state);
}
