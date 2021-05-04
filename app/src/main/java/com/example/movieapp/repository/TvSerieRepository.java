package com.example.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.Episode;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.GenreResponse;
import com.example.movieapp.model.Season;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.model.TvSerieResponse;
import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvSerieRepository {
    private MutableLiveData<List<TvSerie>> popularSeries;
    private MutableLiveData<List<TvSerie>> topRatedSeries;
    private MutableLiveData<TvSerie> serieDetails;
    private MutableLiveData<List<Episode>> episodes;
    private MutableLiveData<List<TvSerie>> trendSeries;
    private MutableLiveData<List<TvSerie>> seriesByGenre;
    private MutableLiveData<List<Genre>> genres;
    private static TvSerieRepository tvSerieRepository;
    private static MovieService movieService;

    public static TvSerieRepository getInstance(){
        if(tvSerieRepository == null){
            tvSerieRepository = new TvSerieRepository();
        }
        return tvSerieRepository;
    }
    public TvSerieRepository(){
        movieService= RetrofitService.getMovieService();
        seriesByGenre = new MutableLiveData<>();
        trendSeries = new MutableLiveData<>();
        popularSeries = new MutableLiveData<>();
        topRatedSeries = new MutableLiveData<>();
        serieDetails = new MutableLiveData<>();
        episodes = new MutableLiveData<>();
        genres = new MutableLiveData<>();
    }
    public void fetchEpisodes(String tv_id,String season_number){
        movieService.getEpisodes(tv_id,season_number).enqueue(new Callback<Season>() {
            @Override
            public void onResponse(Call<Season> call, Response<Season> response) {
                if(response.isSuccessful()){
                    episodes.postValue(response.body().getEpisodes());
                }
            }

            @Override
            public void onFailure(Call<Season> call, Throwable t) {
                episodes.postValue(null);
            }
        });
    }
    public void fetchSerieDetails(int id){
        movieService.getSerieById(id).enqueue(new Callback<TvSerie>() {
            @Override
            public void onResponse(Call<TvSerie> call, Response<TvSerie> response) {
                if(response.isSuccessful()){
                    serieDetails.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TvSerie> call, Throwable t) {
                serieDetails.postValue(null);
            }
        });
    }
    public void fetchTopRatedSeries(){
        movieService.getTopRatedSeries(8,1000,"vote_count.desc","en-US").enqueue(new Callback<TvSerieResponse>() {
            @Override
            public void onResponse(Call<TvSerieResponse> call, Response<TvSerieResponse> response) {
                if(response.isSuccessful()){
                    topRatedSeries.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<TvSerieResponse> call, Throwable t) {
                topRatedSeries.postValue(null);
            }
        });
    }
    public void fetchPopularSeries(){
        movieService.getPopularSeries().enqueue(new Callback<TvSerieResponse>() {
            @Override
            public void onResponse(Call<TvSerieResponse> call, Response<TvSerieResponse> response) {
                if(response.body() != null){
                    popularSeries.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<TvSerieResponse> call, Throwable t) {
                popularSeries.postValue(null);
            }
        });
    }
    public void fetchTrendSeries(){
        movieService.getTrendSeries().enqueue(new Callback<TvSerieResponse>() {
            @Override
            public void onResponse(Call<TvSerieResponse> call, Response<TvSerieResponse> response) {
                if(response.body() != null){
                    trendSeries.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<TvSerieResponse> call, Throwable t) {
                trendSeries.postValue(null);
                Log.v("Tag","Failed");
            }
        });
    }
    public void searchSeriesByGenre(String sort_by,String genreId){
        movieService.getTvSeriesByGenre(sort_by,genreId).enqueue(new Callback<TvSerieResponse>() {
            @Override
            public void onResponse(Call<TvSerieResponse> call, Response<TvSerieResponse> response) {
                if(response.body() != null){
                    seriesByGenre.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<TvSerieResponse> call, Throwable t) {

                seriesByGenre.postValue(null);
            }
        });

    }
    public void fetchSerieGenre(String language){
        movieService.getTvSerieGenres(language).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if(response.body() != null){
                    genres.postValue(response.body().getGenres());
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                genres.postValue(null);
            }
        });

    }
    public MutableLiveData<TvSerie> getSerieDetails() {
        return serieDetails;
    }

    public MutableLiveData<List<Episode>> getEpisodes() {
        return episodes;
    }
    public MutableLiveData<List<TvSerie>> getPopularSeries() {
        return popularSeries;
    }

    public MutableLiveData<List<TvSerie>> getTopRatedSeries() {
        return topRatedSeries;
    }
    public MutableLiveData<List<TvSerie>> getSeriesByGenre() {
        return seriesByGenre;
    }

    public MutableLiveData<List<TvSerie>> getTrendSeries() {
        return trendSeries;
    }

    public MutableLiveData<List<Genre>> getGenres() {
        return genres;
    }
}
