package com.snijsure.flicker;
/**
 * Created by subodhnijsure on 5/4/16.
 */

import com.snijsure.flicker.model.FlickerData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
interface FlickerRetrofitInterface {
    @GET("services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
    Call<FlickerData> getItems(
            @Query("api_key") String key,
            @Query("text") String text,
            @Query("api_sig") String api_sig

    );
}
