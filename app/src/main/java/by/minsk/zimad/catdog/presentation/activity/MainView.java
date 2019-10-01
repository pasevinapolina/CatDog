package by.minsk.zimad.catdog.presentation.activity;

import by.minsk.zimad.catdog.presentation.common.MvpView;

public interface MainView extends MvpView<MainViewState> {
    void openPetList();
    void openPetDetailsScreen(int id);
}
