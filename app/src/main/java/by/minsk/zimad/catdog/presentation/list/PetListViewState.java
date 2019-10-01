package by.minsk.zimad.catdog.presentation.list;

import java.util.List;

import by.minsk.zimad.catdog.presentation.common.PetViewState;

public class PetListViewState {
    private final boolean loading;
    private final boolean error;
    private final List<PetViewState> pets;
    private final TabType tab;

    public PetListViewState(boolean loading, boolean error, List<PetViewState> pets, TabType tab) {
        this.loading = loading;
        this.error = error;
        this.pets = pets;
        this.tab = tab;
    }

    public boolean isLoading() {
        return loading;
    }

    public boolean isError() {
        return error;
    }

    public List<PetViewState> getPets() {
        return pets;
    }

    public TabType getTab() {
        return tab;
    }

    enum TabType {
        CAT, DOG
    }
}
