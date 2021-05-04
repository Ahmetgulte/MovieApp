package com.example.movieapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapp.database.WatchListDatabase;
import com.example.movieapp.database.WatchListShow;

import java.util.List;

public class WatchListViewModel extends AndroidViewModel {
    private LiveData<List<WatchListShow>> shows;
    public WatchListViewModel(@NonNull Application application) {
        super(application);
        WatchListDatabase database = WatchListDatabase.getInstance(this.getApplication());
        shows = database.showDao().loadAllTasks();
    }

    public LiveData<List<WatchListShow>> getShows() {
        return shows;
    }
}

