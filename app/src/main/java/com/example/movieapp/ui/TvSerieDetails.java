package com.example.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.Season;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.ui.adapter.SeasonAdapter;
import com.example.movieapp.viewmodel.SerieViewModel;

import java.util.ArrayList;

public class TvSerieDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView star;
    ImageView time;
    ImageView background;
    TextView title;
    TextView details;
    TextView vote_average;
    TextView runtime;
    SerieViewModel serieViewModel;
    ArrayList<Season> seasons1 ;
    SeasonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_serie_details);
        recyclerView = findViewById(R.id.season_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        background = findViewById(R.id.serie_background_image);
        title = findViewById(R.id.serie_detail_title);
        details = findViewById(R.id.serie_detail);
        Intent intent = getIntent();
        int serieId=intent.getIntExtra("serieId",0);
        serieViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(SerieViewModel.class);
        serieViewModel.fetchSerieDetails(serieId);
        serieViewModel.getSerieDetail().observe(this, new Observer<TvSerie>() {
            @Override
            public void onChanged(TvSerie tvSerie) {
                String imageUrl="https://image.tmdb.org/t/p/original";
                imageUrl +=tvSerie.getPoster();
                title.setText(tvSerie.getName());
                details.setText(tvSerie.getOverview());
                Glide.with(TvSerieDetails.this).load(imageUrl).centerCrop().into(background);
                ArrayList<Season> seasons = tvSerie.getSeasons();
                SeasonAdapter adapter = new SeasonAdapter(getApplicationContext(),seasons);
                recyclerView.setAdapter(adapter);
            }


        });








    }

    }
