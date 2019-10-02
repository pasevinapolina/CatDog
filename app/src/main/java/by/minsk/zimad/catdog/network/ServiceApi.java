package by.minsk.zimad.catdog.network;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("api.php")
    Single<BaseResponse<List<PetResponse>>> getPets(@Query("query") String type);
}
