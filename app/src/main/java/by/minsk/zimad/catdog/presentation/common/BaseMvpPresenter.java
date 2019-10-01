package by.minsk.zimad.catdog.presentation.common;

import android.support.annotation.CallSuper;

public abstract class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {
    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
        onViewAttached();
    }

    protected abstract void onViewAttached();

    @Override
    @CallSuper
    public void onDestroy() {
        this.view = null;
    }
}
