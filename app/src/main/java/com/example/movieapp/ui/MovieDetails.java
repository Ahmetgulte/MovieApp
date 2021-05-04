package com.example.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.ui.adapter.CastAdapter;
import com.example.movieapp.R;
import com.example.movieapp.model.Cast;
import com.example.movieapp.model.Movie;
import com.example.movieapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView star;
    ImageView time;
    ImageView background;
    TextView title;
    TextView details;
    TextView vote_average;
    TextView runtime;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        recyclerView=findViewById(R.id.movie_detail_actors);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        background=findViewById(R.id.background_image);
        details=findViewById(R.id.movie_detail);
        title = findViewById(R.id.movie_detail_title);
        star = findViewById(R.id.movie_detail_star);
        time = findViewById(R.id.movie_detail_time);
        vote_average = findViewById(R.id.movie_detail_vote);
        runtime = findViewById(R.id.movie_detail_runtime);
        Intent intent=getIntent();
        int movieId=intent.getIntExtra("movieId",0);
        Log.v("Tag", String.valueOf(movieId));

        movieViewModel =new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MovieViewModel.class);
        movieViewModel.searchMovieById(movieId);
        movieViewModel.getMovieById().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                String imageUrl="https://image.tmdb.org/t/p/original";
                imageUrl +=movie.getBackdrop_path();
                Glide.with(MovieDetails.this).load(imageUrl).centerCrop().placeholder(R.drawable.ic_launcher_background).into(background);
                star.setImageResource(R.drawable.star);
                time.setImageResource(R.drawable.time);
                details.setText(movie.getOverview());
                title.setText(movie.getTitle());
                vote_average.setText(movie.getVote_average()+"/10.0");
                runtime.setText(movie.getRuntime() / 60+"h "+ movie.getRuntime() %60+"min");


            }
        });

        movieViewModel.searchMovieActors(movieId);
        movieViewModel.getMovieActors().observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                ArrayList<Cast> casts1=new ArrayList<>(casts);
                CastAdapter adapter=new CastAdapter(MovieDetails.this,casts1) ;
                recyclerView.setAdapter(adapter);

            }
        });

    }
}