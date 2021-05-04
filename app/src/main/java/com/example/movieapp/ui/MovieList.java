package com.example.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.movieapp.R;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.ui.adapter.MovieAdapter;
import com.example.movieapp.ui.adapter.TvSerieAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;
import com.example.movieapp.viewmodel.SerieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieList extends AppCompatActivity {
    MovieViewModel viewModel;
    SerieViewModel serieViewModel;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        recyclerView = findViewById(R.id.movie_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
        String media_type =intent.getStringExtra("media_type");
        String type =intent.getStringExtra("type");
        int id = intent.getIntExtra("id",0);


        if(media_type.equals("movie")){
            viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MovieViewModel.class);
            if(type.equals("genre")){
                getMoviesByGenre(String.valueOf(id));
            }
            else if (type.equals("trend")){
                getTrendMovies();
            }
            else if(type.equals("popular")){
                getPopularMovies();
            }
            else if(type.equals("topRated")){
                getTopRatedMovies();
            }
            else if(type.equals("search")){
                String query = intent.getStringExtra("query");
                getSearchedMovies(query);
            }
        }
        else{
            serieViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(SerieViewModel.class);
            if(type.equals("genre")){
                getSeriesByGenre(String.valueOf(id));
            }
            else if (type.equals("trend")){
                getTrendSeries();
            }
            else if(type.equals("popular")){
                getPopularSeries();
            }
            else if(type.equals("topRated")){
                getTopRatedSeries();
            }
        }

    }

    private void getSeriesByGenre(String id) {
        serieViewModel.fetchSerieByGenre("popularity.desc",id);
        serieViewModel.getSeriesByGenre().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> arrayList = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getSearchedMovies(String query) {
        viewModel.getSearchedMovie(query);
        viewModel.getMovieSearched().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getTopRatedSeries() {
        serieViewModel.fetchTopRatedSeries();
        serieViewModel.getTopRatedSeries().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> arrayList = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getPopularSeries() {
        serieViewModel.fetchPopularSeries();
        serieViewModel.getPopularSeries().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> arrayList = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getTrendSeries() {
        serieViewModel.fetchTrendSeries();
        serieViewModel.getTrendSeries().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> arrayList = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getTopRatedMovies() {
        viewModel.fetchTopRatedMovies();
        viewModel.getTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void getPopularMovies() {
        viewModel.fetchPopularMovies();
        viewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void getTrendMovies() {
        viewModel.fetchTrendMovies();
        viewModel.getTrendMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
    public void getMoviesByGenre(String id){
        viewModel.searchMoviesByGenre("popularity.desc",id);
        viewModel.getListOfMoviesByGenre().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(MovieList.this,arrayList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

 /*   private void getMoviesByGenre(String id) {
        viewModel.searchMoviesByGenre("popularity.desc",id);
        viewModel.getListOfMoviesByGenre().observe(this, new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> movies) {
                ArrayList<Show> shows=new ArrayList<>();
                for(int i =0 ; i<movies.size(); i++){
                    movies.get(i).setMedia_type("movie");
                    shows.add(movies.get(i));
                }
                MovieAdapter adapter = new MovieAdapter(MovieList.this,shows);
                recyclerView.setAdapter(adapter);
            }
        });
    }*/
}