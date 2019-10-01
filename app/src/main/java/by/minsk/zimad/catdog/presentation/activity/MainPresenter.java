package by.minsk.zimad.catdog.presentation.activity;

import by.minsk.zimad.catdog.presentation.common.MvpPresenter;

public interface MainPresenter extends MvpPresenter<MainView> {
    void onPetClicked(int id);
}
