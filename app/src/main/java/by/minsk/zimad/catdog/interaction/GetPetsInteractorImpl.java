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
                    Log.d("PetList", "getPets " + type + ", response: " + baseResponse.getData());
                    if (baseResponse.isSuccessful()) {
                        final List<Pet> pets = Collections.emptyList();
                        List<PetResponse> responseData = baseResponse.getData();
                        for (PetResponse pet : responseData) {
                            pets.add(new Pet(type, pet.getImageUrl(), pet.getTitle()));
                        }
                        return new InteractionResult<List<Pet>>(true, pets, null);
                    } else {
                        return new InteractionResult<List<Pet>>(
                                false,
                                null,
                                new Throwable(baseResponse.getMessage())
                        );
                    }
                });
    }

    private String getQuery(Pet.Type type) {
        if (type == CAT)
            return "cat";
        else {
            return "dog";
        }
    }
}
