package com.snijsure.flicker;
/**
 * Created by subodhnijsure on 5/4/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.snijsure.flicker.data.PhotoProvider;
import com.snijsure.flicker.model.FlickerData;
import com.snijsure.flicker.model.Photo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView photo_list;
    private static String TAG = "MainActivity";
    FlickerDataAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo_list = (RecyclerView)findViewById(R.id.photo_list);
        photo_list.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        photo_list.setLayoutManager(linearLayoutManager);


        /*
        // Code to test UI without actually invoking
        PhotoProvider provider = new PhotoProvider();
        List<Photo>photoData = provider.getPhotoData();
        adapter = new FlickerDataAdapter(photoData);
        photo_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        */
        List<Photo> items =  new ArrayList<Photo>();
        adapter = new FlickerDataAdapter(items);
        photo_list.setAdapter(adapter);
        startDownload();
    }


    void startDownload() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlickerRetrofitInterface retrofitService = retrofit.create(FlickerRetrofitInterface.class);
        Call<FlickerData> call = retrofitService.getItems(
                getApplicationContext().getString(R.string.FLICKER_API_KEY),
                "guitar",
                getApplicationContext().getString(R.string.FLICKER_API_SIG)
            );

        call.enqueue(new Callback<FlickerData>() {
            @Override
            public void onResponse(Call<FlickerData> call, Response<FlickerData> response) {
                FlickerData feed = response.body();
                if ( feed.getPhotos() != null ) {
                    List<Photo> mItems = feed.getPhotos().getPhoto();
                    adapter.setData(mItems);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FlickerData> call, Throwable t) {
                Log.d(TAG, "OnFailure Error is " + t);
            }
        });
    }
}
