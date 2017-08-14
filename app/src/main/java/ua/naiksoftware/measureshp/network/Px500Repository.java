package ua.naiksoftware.measureshp.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.naiksoftware.measureshp.model.Px500SearchResponse;

/**
 * Created by naik on 13.08.17.
 */

public interface Px500Repository {

    @GET("photos/search")
    Single<Px500SearchResponse> search(@Query("term") String keyword,      // birds
                                       @Query("only") String categories,   // Fashion,Travel...
                                       @Query("exclude_nude") int safe,    // 1 or 0
                                       @Query("image_size") int imageSize, // Px500Photo.SIZE_{...}
                                       @Query("page") int page);           // 1, 2...
}
