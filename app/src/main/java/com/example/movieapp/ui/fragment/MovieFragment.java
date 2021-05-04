package com.example.movieapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.ui.adapter.GenreAdapter;
import com.example.movieapp.ui.MovieList;
import com.example.movieapp.R;
import com.example.movieapp.model.Genre;
import com.example.movieapp.model.Movie;
import com.example.movieapp.ui.adapter.MovieAdapter;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {
    LinearLayout linearLayout;
    TextView trendSeeAll;
    TextView popularSeeAll;
    TextView topRatedSeeAll;
    RecyclerView trendMovie;
    RecyclerView genre_recycler;
    RecyclerView topRated_recycler;
    RecyclerView popular_recycler;
    ImageView search_icon;
    MovieViewModel movieViewModel;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_movie,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        linearLayout = view.findViewById(R.id.search);
        search_icon = view.findViewById(R.id.search_button_image);
        trendSeeAll = view.findViewById(R.id.trend_movie_see_all);
        popularSeeAll = view.findViewById(R.id.popular_movie_see_all);
        topRatedSeeAll = view.findViewById(R.id.topRated_movie_see_all);
        popular_recycler = view.findViewById(R.id.popular_movies);
        genre_recycler = view.findViewById(R.id.movie_genres);
        topRated_recycler = view.findViewById(R.id.topRated_movies);
        trendMovie= view.findViewById(R.id.trend_movies);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        trendMovie.setLayoutManager(layoutManager);
        trendMovie.setHasFixedSize(true);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        genre_recycler.setLayoutManager(layoutManager1);
        genre_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        popular_recycler.setLayoutManager(layoutManager2);
        popular_recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager3=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        topRated_recycler.setLayoutManager(layoutManager3);
        topRated_recycler.setHasFixedSize(true);


        Glide.with(getContext()).load("https://lh3.googleusercontent.com/proxy/JZaY73KcKu-39lYOILYIT7-8W0nu-j50i9rCsiwrJOsiYkeI535va67q2MHKqcGDqTKfS2kmUmsI1JGuBSMq-KIwYcJxSF-JV3oN1Q").fitCenter().into(search_icon);
        movieViewModel =new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MovieViewModel.class);
        movieViewModel.fetchTrendMovies();
        movieViewModel.getTrendMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(getContext(),arrayList);
                trendMovie.setAdapter(adapter);
            }
        });
        movieViewModel.fetchMovieGenres("en-US");
        movieViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                ArrayList<Genre> genres1 = new ArrayList<>(genres);
                GenreAdapter adapter=new GenreAdapter(getContext(),genres1,"movie");
                genre_recycler.setAdapter(adapter);

            }
        });
        movieViewModel.fetchPopularMovies();
        movieViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(getContext(),arrayList);
                popular_recycler.setAdapter(adapter);
            }
        });
        movieViewModel.fetchTopRatedMovies();
        movieViewModel.getTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                ArrayList<Movie> arrayList = new ArrayList<>(movies);
                MovieAdapter adapter = new MovieAdapter(getContext(),arrayList);
                topRated_recycler.setAdapter(adapter);
            }
        });

        trendSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","movie");
                intent.putExtra("type","trend");
                startActivity(intent);
            }
        });
        popularSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","movie");
                intent.putExtra("type","popular");
                startActivity(intent);
            }
        });
        topRatedSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","movie");
                intent.putExtra("type","topRated");
                startActivity(intent);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_layout,new SearchFragment()).commit();
            }
        });


    }
}
