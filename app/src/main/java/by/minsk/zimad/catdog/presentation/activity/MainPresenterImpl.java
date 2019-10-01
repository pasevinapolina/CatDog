package by.minsk.zimad.catdog.presentation.activity;

import by.minsk.zimad.catdog.presentation.common.BaseMvpPresenter;

public class MainPresenterImpl extends BaseMvpPresenter<MainView> implements MainPresenter {

    @Override
    protected void onViewAttached() {
        view.openPetList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPetClicked(int id) {
        view.openPetDetailsScreen(id);
    }
}
