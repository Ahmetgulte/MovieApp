package com.example.movieapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.database.WatchListDatabase;
import com.example.movieapp.database.WatchListShow;
import com.example.movieapp.ui.adapter.WatchListAdapter;

import java.util.List;

public class WatchlistFragment extends Fragment {
    private WatchListDatabase database;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist,container,false);
        recyclerView = view.findViewById(R.id.watchlist_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        database = WatchListDatabase.getInstance(getContext());
        database.showDao().loadAllTasks().observe(this, new Observer<List<WatchListShow>>() {
            @Override
            public void onChanged(List<WatchListShow> watchListShows) {
                WatchListAdapter adapter = new WatchListAdapter(getContext(),watchListShows);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }
}
