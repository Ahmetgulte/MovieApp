package com.example.movieapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapp.R;
import com.example.movieapp.ui.fragment.TvSerieFragment;
import com.example.movieapp.ui.fragment.MovieFragment;
import com.example.movieapp.ui.fragment.WatchlistFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new MovieFragment()).commit();
        /*



         */
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment =null;
            switch (item.getItemId()){
                case R.id.movie_fragment :
                    selectedFragment = new MovieFragment();
                    break;
                case R.id.serie_fragment:
                    selectedFragment=new TvSerieFragment();
                    break;
                case R.id.watchlist:
                    selectedFragment=new WatchlistFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,selectedFragment).commit();
            return true;
        }
    };
}