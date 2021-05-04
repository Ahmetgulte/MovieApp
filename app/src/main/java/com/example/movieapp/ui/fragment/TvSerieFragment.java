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
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.ui.adapter.TvSerieAdapter;
import com.example.movieapp.viewmodel.SerieViewModel;

import java.util.ArrayList;
import java.util.List;

public class TvSerieFragment extends Fragment {
    TextView trendSeeAll;
    TextView popularSeeAll;
    TextView topRatedSeeAll;
    ImageView search_icon;
    RecyclerView trendSerie;
    RecyclerView genre_serie_recycler;
    RecyclerView popularSeries;
    RecyclerView topRatedSeries;
    SerieViewModel serieViewModel;
    LinearLayout linearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_serie,container,false);

        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        linearLayout = view.findViewById(R.id.search);
        trendSeeAll = view.findViewById(R.id.serie_trend_see_all);
        popularSeeAll = view.findViewById(R.id.popular_serie_see_all);
        topRatedSeeAll = view.findViewById(R.id.topRated_serie_see_all);
        popularSeries = view.findViewById(R.id.popular_series);
        genre_serie_recycler = view.findViewById(R.id.serie_genres);
        topRatedSeries = view.findViewById(R.id.topRated_series);
        trendSerie = view.findViewById(R.id.trend_series);
       LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        trendSerie.setLayoutManager(layoutManager);
        trendSerie.setHasFixedSize(true);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        popularSeries.setLayoutManager(layoutManager1);
        popularSeries.setHasFixedSize(true);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        topRatedSeries.setLayoutManager(layoutManager2);
        LinearLayoutManager layoutManager3=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        genre_serie_recycler.setLayoutManager(layoutManager3);
        genre_serie_recycler.setHasFixedSize(true);
        search_icon = view.findViewById(R.id.search_button_image);
        Glide.with(getContext()).load("https://lh3.googleusercontent.com/proxy/JZaY73KcKu-39lYOILYIT7-8W0nu-j50i9rCsiwrJOsiYkeI535va67q2MHKqcGDqTKfS2kmUmsI1JGuBSMq-KIwYcJxSF-JV3oN1Q").fitCenter().into(search_icon);
        ;


        serieViewModel =new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(SerieViewModel.class);
        serieViewModel.fetchTrendSeries();
        serieViewModel.getTrendSeries().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> tvSeries1 = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(getContext(),tvSeries1);
                trendSerie.setAdapter(adapter);
            }
        });
        serieViewModel.fetchPopularSeries();
        serieViewModel.getPopularSeries().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> tvSeries1 = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(getContext(),tvSeries1);
                popularSeries.setAdapter(adapter);
            }
        });
        serieViewModel.fetchTopRatedSeries();
        serieViewModel.getTopRatedSeries().observe(this, new Observer<List<TvSerie>>() {
            @Override
            public void onChanged(List<TvSerie> tvSeries) {
                ArrayList<TvSerie> tvSeries1 = new ArrayList<>(tvSeries);
                TvSerieAdapter adapter = new TvSerieAdapter(getContext(),tvSeries1);
                topRatedSeries.setAdapter(adapter);
            }
        });
        serieViewModel.fetchSerieGenres();
        serieViewModel.getSerieGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                ArrayList<Genre> genres1 = new ArrayList<>(genres);
                GenreAdapter adapter = new GenreAdapter(getContext(),genres1,"serie");
                genre_serie_recycler.setAdapter(adapter);
            }
        });
        trendSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","tv");
                intent.putExtra("type","trend");
                startActivity(intent);
            }
        });
        topRatedSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","tv");
                intent.putExtra("type","topRated");
                startActivity(intent);
            }
        });
        popularSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","tv");
                intent.putExtra("type","popular");
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
