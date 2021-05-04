package com.example.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.example.movieapp.model.Cast;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;

import java.util.List;



public class MovieViewModel extends AndroidViewModel {
    private MutableLiveData<List<Movie>> trendMovies=new MutableLiveData<>();
    private MutableLiveData<List<Movie>> movieSearched=new MutableLiveData<>();
    private MutableLiveData<List<Movie>> listOfMoviesByGenre = new MutableLiveData<>();
    private MutableLiveData<Movie> movieById=new MutableLiveData<>();
    private MutableLiveData<List<Cast>> movieActors = new MutableLiveData<>();
    private MutableLiveData<List<Genre>> genres = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> popularMovies = new MutableLiveData<>();
    private MutableLiveData<List<Movie>> topRatedMovies = new MutableLiveData<>();

    private MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }
    public void getSearchedMovie(String query){
        movieRepository.searchMovie(query);
    }

    public void fetchPopularMovies(){
        movieRepository.fetchPopularMovies();
    }
    public void fetchTopRatedMovies (){
        movieRepository.fetchTopRatedMovies();
    }
    public  void fetchMovieGenres(String language){
        movieRepository.searchMovieGenre(language);
    }
    public void searchMoviesByGenre(String sort_by,String genreId){
        movieRepository.searchMoviesByGenre(sort_by,genreId);
    }
    public void searchMovieById(int movieId){
        movieRepository.searchMovieById(movieId);
    }
    public void searchMovieActors(int movieId){
        movieRepository.searchMovieActors(movieId);
    }

    public void fetchTrendMovies(){
        movieRepository.fetchTrendMovies();
    }

    public MutableLiveData<List<Movie>> getListOfMoviesByGenre() {
        listOfMoviesByGenre = movieRepository.getMoviesByGenre();
        return listOfMoviesByGenre;
    }

    public MutableLiveData<Movie> getMovieById() {
        movieById = movieRepository.getMovieById();
        return movieById;
    }

    public MutableLiveData<List<Cast>> getMovieActors() {
        movieActors = movieRepository.getMovieActors();
        return movieActors;
    }


    public MutableLiveData<List<Movie>> getTrendMovies() {
        trendMovies = movieRepository.getTrendMovies();
        return trendMovies;
    }

    public MutableLiveData<List<Genre>> getGenres() {
        genres=movieRepository.getGenres();
        return genres;
    }

    public MutableLiveData<List<Movie>> getPopularMovies() {
        popularMovies = movieRepository.getPopularMovies();
        return popularMovies;
    }

    public MutableLiveData<List<Movie>> getTopRatedMovies() {
        topRatedMovies = movieRepository.getTopRatedMovies();
        return topRatedMovies;
    }

    public MutableLiveData<List<Movie>> getMovieSearched() {
        movieSearched = movieRepository.getMovieSearched();
        return movieSearched;
    }
}
