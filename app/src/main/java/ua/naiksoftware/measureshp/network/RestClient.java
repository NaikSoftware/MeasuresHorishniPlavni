package ua.naiksoftware.measureshp.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;
import ua.naiksoftware.measureshp.BuildConfig;

/**
 * Created by naik on 13.08.17.
 */

public class RestClient {

    private static final Retrofit RETROFIT_500PX = new Retrofit.Builder()
            .baseUrl("https://api.500px.com/v1/")
            .client(create500pxClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
            .build();

    private static OkHttpClient create500pxClient() {
        return new okhttp3.OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Timber.d(original.toString());
            Request.Builder builder = chain.request().newBuilder()
                    .headers(chain.request().headers())
                    .method(original.method(), original.body())
                    .url(original.url().newBuilder().addQueryParameter("consumer_key", BuildConfig.PX500_CONSUMER_KEY).build());
            return chain.proceed(builder.build());
        }).build();
    }

    private static Px500Repository px500Repository;

    public static Px500Repository getPx500Repository() {
        if (px500Repository == null) {
            px500Repository = RETROFIT_500PX.create(Px500Repository.class);
        }
        return px500Repository;
    }
}
