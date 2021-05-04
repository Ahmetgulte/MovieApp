package com.example.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.model.Episode;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.TvSerieRepository;

import java.util.List;

public class SerieViewModel extends AndroidViewModel {
    private TvSerieRepository tvSerieRepository;
    private MutableLiveData<List<TvSerie>> trendSeries = new MutableLiveData<>();
    private MutableLiveData<List<TvSerie>> topRatedSeries= new MutableLiveData<>();
    private MutableLiveData<List<Genre>> serieGenres = new MutableLiveData<>();
    private MutableLiveData<List<TvSerie>> popularSeries = new MutableLiveData<>();
    private MutableLiveData<List<TvSerie>> seriesByGenre = new MutableLiveData<>();
    private MutableLiveData<TvSerie> serieDetail = new MutableLiveData<>();
    private MutableLiveData<List<Episode>> episodes = new MutableLiveData<>();

    public SerieViewModel(@NonNull Application application) {
        super(application);
        tvSerieRepository=TvSerieRepository.getInstance();
    }

    public void fetchSerieDetails(int id){
        tvSerieRepository.fetchSerieDetails(id);
    }
    public void fetchTrendSeries(){
        tvSerieRepository.fetchTrendSeries();
    }
    public void fetchPopularSeries(){
        tvSerieRepository.fetchPopularSeries();
    }
    public void fetchTopRatedSeries(){
        tvSerieRepository.fetchTopRatedSeries();
    }
    public void fetchSerieGenres(){
        tvSerieRepository.fetchSerieGenre("en-US");
    }

    public void fetchSerieByGenre(String sort_by,String id){
        tvSerieRepository.searchSeriesByGenre(sort_by,id);
    }
    public void fetchEpisodes(String tv_id,String season_number){
        tvSerieRepository.fetchEpisodes(tv_id,season_number);
    }
    public MutableLiveData<List<TvSerie>> getTrendSeries() {
        trendSeries = tvSerieRepository.getTrendSeries();
        return trendSeries;
    }

    public MutableLiveData<List<TvSerie>> getTopRatedSeries() {
        topRatedSeries = tvSerieRepository.getTopRatedSeries();
        return topRatedSeries;
    }

    public MutableLiveData<List<Genre>> getSerieGenres() {
        serieGenres = tvSerieRepository.getGenres();
        return serieGenres;
    }

    public MutableLiveData<List<TvSerie>> getPopularSeries() {
        popularSeries = tvSerieRepository.getPopularSeries();
        return popularSeries;
    }

    public MutableLiveData<List<TvSerie>> getSeriesByGenre() {
        seriesByGenre = tvSerieRepository.getSeriesByGenre();
        return seriesByGenre;
    }

    public MutableLiveData<TvSerie> getSerieDetail() {
        serieDetail = tvSerieRepository.getSerieDetails();
        return serieDetail;
    }

    public MutableLiveData<List<Episode>> getEpisodes() {
        episodes = tvSerieRepository.getEpisodes();
        return episodes;
    }
}
