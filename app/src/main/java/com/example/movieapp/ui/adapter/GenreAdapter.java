package com.example.movieapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.ui.MovieList;
import com.example.movieapp.R;
import com.example.movieapp.model.Genre;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{
    private Context context;
    private String media_type;
    private ArrayList<Genre> genres;

    public GenreAdapter(Context context, ArrayList<Genre> genres,String media_type) {
        this.context = context;
        this.genres = genres;
        this.media_type = media_type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.genre_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre currnetGenre = genres.get(position);
        holder.button.setText(currnetGenre.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieList.class);
                intent.putExtra("media_type",media_type);
                intent.putExtra("type","genre");
                intent.putExtra("id",currnetGenre.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.genre_button);

        }
    }
}
