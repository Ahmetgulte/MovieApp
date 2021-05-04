package com.example.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.Cast;
import com.example.movieapp.model.Episode;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.GenreResponse;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieCredit;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.model.Season;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.model.TvSerieResponse;

import com.example.movieapp.retrofit.MovieService;
import com.example.movieapp.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MutableLiveData<List<Movie>> topRatedMovies;
    private MutableLiveData<List<Movie>> trendMovies;

    private MutableLiveData<List<Movie>> movieSearched;
    private static MovieService movieService;
    private static MovieRepository newsRepository;
    private MutableLiveData<List<Genre>> genres;
    private MutableLiveData<List<Movie>> moviesByGenre;

    private MutableLiveData<Movie> movieById;
    private MutableLiveData<List<Cast>> movieActors;
    private MutableLiveData<List<Movie>> popularMovies;


    public static MovieRepository getInstance(){
        if(newsRepository == null){
            newsRepository = new MovieRepository();
        }
        return newsRepository;
    }
    public MovieRepository(){

        movieSearched=new MutableLiveData<>();
        genres =new MutableLiveData<>();
        movieService= RetrofitService.getMovieService();
        moviesByGenre=new MutableLiveData<>();
        movieById = new MutableLiveData<>();
        movieActors = new MutableLiveData<>();
        trendMovies = new MutableLiveData<>();
        popularMovies = new MutableLiveData<>();
        topRatedMovies = new MutableLiveData<>();


    }
    public void searchMovie(String query){
        movieService.searchMovie(query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null){
                    movieSearched.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                movieSearched.postValue(null);
            }
        });
    }



    public void fetchTopRatedMovies(){
        movieService.getTopRatedMovies(8,1000,"vote_count.desc","en-US").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful())
                    topRatedMovies.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                topRatedMovies.postValue(null);
            }
        });
    }

    public void fetchPopularMovies(){
        movieService.getPopularMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful())
                    popularMovies.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                popularMovies.postValue(null);
            }
        });
    }

    public void fetchTrendMovies(){
        movieService.getTrendMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null){
                    trendMovies.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                trendMovies.postValue(null);

            }
        });
    }





    public void searchMovieGenre(String language){
        movieService.getMovieGenres(language).enqueue(new Callback<GenreResponse>() {
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

    public void searchMoviesByGenre(String sort_by,String genreId){
        movieService.getMoviesByGenre(sort_by,genreId).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null){
                    moviesByGenre.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                moviesByGenre.postValue(null);
            }
        });
    }

    public void searchMovieById(int movieId){
        movieService.getMovieById(movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.body() != null){
                    movieById.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                movieById.postValue(null);
            }
        });
    }
    public void searchMovieActors(int movieId){
        movieService.getMovieActors(movieId).enqueue(new Callback<MovieCredit>() {
            @Override
            public void onResponse(Call<MovieCredit> call, Response<MovieCredit> response) {
                if(response.body() != null){
                    movieActors.postValue(response.body().getCast());
                }
            }

            @Override
            public void onFailure(Call<MovieCredit> call, Throwable t) {
                movieActors.postValue(null);
            }
        });
    }

    public MutableLiveData<List<Movie>> getTrendMovies() {
        return trendMovies;
    }

    public MutableLiveData<List<Genre>> getGenres() {
        return genres;
    }

    public MutableLiveData<List<Movie>> getMoviesByGenre() {
        return moviesByGenre;
    }

    public MutableLiveData<List<Cast>> getMovieActors() {
        return movieActors;
    }

    public MutableLiveData<Movie> getMovieById() {
        return movieById;
    }

    public MutableLiveData<List<Movie>> getMovieSearched() {
        return movieSearched;
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        return popularMovies;
    }

    public MutableLiveData<List<Movie>> getTopRatedMovies() {
        return topRatedMovies;
    }


}
