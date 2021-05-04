package com.example.movieapp.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String BASE_URL ="https://api.themoviedb.org";
    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl httpUrl=original.url();
                    HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("api_key","9e2629973011b0744ce3b589dff1fb32").build();
                    Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            })
            .build();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static MovieService getMovieService(){
        return retrofit.create(MovieService.class);
    }
}

