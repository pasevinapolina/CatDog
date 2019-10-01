package by.minsk.zimad.catdog.presentation.list;

import by.minsk.zimad.catdog.presentation.common.MvpPresenter;

public interface PetListPresenter extends MvpPresenter<PetListView> {
    void onTabClicked(PetListViewState.TabType tab);
    void onPetClicked(int id);
    void onDestroy();
}
