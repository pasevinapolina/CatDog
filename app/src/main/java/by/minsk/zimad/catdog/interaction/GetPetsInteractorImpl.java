package by.minsk.zimad.catdog.interaction;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import by.minsk.zimad.catdog.entities.Pet;
import by.minsk.zimad.catdog.network.NetworkService;
import by.minsk.zimad.catdog.network.PetResponse;
import by.minsk.zimad.catdog.network.ServiceApi;
import io.reactivex.Single;

import static by.minsk.zimad.catdog.entities.Pet.Type.CAT;

public class GetPetsInteractorImpl implements GetPetsInteractor {

    @Override
    public Single<InteractionResult<List<Pet>>> get(Pet.Type type) {
        final String query = getQuery(type);
        final ServiceApi api = NetworkService.getInstance().getServiceApi();
        Log.d("PetList", "get " + query);

        return api.getPets(query)
                .map(baseResponse -> {
                    Log.d("PetList", "getPets " + type + ", response: " + baseResponse);
                    List<Pet> pets = Collections.emptyList();
                    List<PetResponse> responseData = baseResponse.getData();
                    for (PetResponse petResponse : responseData) {
                        Log.d("PetList", "add pet " + petResponse);
                        Pet pet = new Pet(type, petResponse.getImageUrl(), petResponse.getTitle());
                        pets.add(pet);
                    }
                    return new InteractionResult<List<Pet>>(true, pets, null);
                })
                .onErrorReturn(throwable -> new InteractionResult<List<Pet>>(false, null, throwable));
    }

    private String getQuery(Pet.Type type) {
        if (type == CAT)
            return "cat";
        else {
            return "dog";
        }
    }
}
