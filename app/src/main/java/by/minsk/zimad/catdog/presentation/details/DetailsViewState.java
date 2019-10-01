package by.minsk.zimad.catdog.presentation.details;

import by.minsk.zimad.catdog.presentation.common.PetViewState;

public class DetailsViewState {
    private final boolean loading;
    private final PetViewState pet;

    public DetailsViewState(boolean loading, PetViewState pet) {
        this.loading = loading;
        this.pet = pet;
    }

    public boolean isLoading() {
        return loading;
    }

    public PetViewState getPet() {
        return pet;
    }
}
