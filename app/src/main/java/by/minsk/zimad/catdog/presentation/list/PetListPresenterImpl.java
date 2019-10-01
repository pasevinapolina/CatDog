package by.minsk.zimad.catdog.presentation.list;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import by.minsk.zimad.catdog.entities.Pet;
import by.minsk.zimad.catdog.interaction.GetPetsInteractor;
import by.minsk.zimad.catdog.interaction.GetPetsInteractorImpl;
import by.minsk.zimad.catdog.presentation.common.BaseMvpPresenter;
import by.minsk.zimad.catdog.presentation.common.PetViewState;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

public class PetListPresenterImpl extends BaseMvpPresenter<PetListView> implements PetListPresenter {

    private final GetPetsInteractor getPetsInteractor = new GetPetsInteractorImpl();

    private Disposable disposable = Disposables.disposed();

    @Override
    protected void onViewAttached() {
        Pet.Type type = Pet.Type.CAT;
        Log.d("PetList", "onViewAttached");
        disposable = getPetsInteractor.get(type)
                .doOnSubscribe(disposable -> view.showLoading())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(contract -> {
                    List<PetViewState> petViewState = Collections.emptyList();

                    PetListViewState.TabType tab = toViewState(type);

                    if (contract.isSuccess()) {
                        List<Pet> pets = contract.getData();
                        Pet pet;
                        for (int i = 0; i < pets.size(); i++) {
                            pet = pets.get(i);
                            petViewState.add(
                                    new PetViewState(i + 1, pet.getTitle(), pet.getImageUrl())
                            );
                        }
                    }
                    return new PetListViewState(false, contract.isSuccess(), petViewState, tab);
                })
                .subscribe(state -> view.render(state));
    }

    private PetListViewState.TabType toViewState(Pet.Type type) {
        if (type == Pet.Type.DOG) {
            return PetListViewState.TabType.DOG;
        } else {
            return PetListViewState.TabType.CAT;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    @Override
    public void onTabClicked(PetListViewState.TabType tab) {

    }

    @Override
    public void onPetClicked(int id) {
        view.openPetDetailsScreen(id);
    }
}
