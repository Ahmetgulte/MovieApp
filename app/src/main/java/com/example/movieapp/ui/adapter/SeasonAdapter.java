package com.example.movieapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieapp.R;
import com.example.movieapp.model.Season;

import java.util.ArrayList;


public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> {
    private Context context;
    private  ArrayList<Season> seasons;

    public SeasonAdapter(Context context, ArrayList<Season> seasons) {
        this.context = context;
        this.seasons = seasons;
    }

    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.season_item,parent,false);
        return new SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder holder, int position) {
        Season season = seasons.get(position);
        holder.seasonName.setText(season.getName());
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    class SeasonViewHolder extends RecyclerView.ViewHolder{
        TextView seasonName;
        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);
            seasonName = itemView.findViewById(R.id.seasons);
        }
    }

}
