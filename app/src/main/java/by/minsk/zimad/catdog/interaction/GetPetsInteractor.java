package by.minsk.zimad.catdog.interaction;

import java.util.List;

import by.minsk.zimad.catdog.entities.Pet;
import io.reactivex.Single;

public interface GetPetsInteractor {
    Single<InteractionResult<List<Pet>>> get(Pet.Type type);
}
