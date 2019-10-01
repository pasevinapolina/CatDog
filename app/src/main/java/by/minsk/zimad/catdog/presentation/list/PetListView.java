package by.minsk.zimad.catdog.presentation.list;

import java.util.List;

import by.minsk.zimad.catdog.presentation.common.MvpView;
import by.minsk.zimad.catdog.presentation.common.PetViewState;

public interface PetListView extends MvpView<PetListViewState> {
    void showLoading();
    void hideLoading();

    void setPets(List<PetViewState> pets);

    void showError();
    void hideError();

    void openPetDetailsScreen(int id);
}
