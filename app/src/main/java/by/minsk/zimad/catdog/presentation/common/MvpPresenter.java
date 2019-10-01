package by.minsk.zimad.catdog.presentation.common;

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void onDestroy();
}
