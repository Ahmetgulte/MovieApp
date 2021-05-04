package com.example.movieapp.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.database.WatchListDatabase;
import com.example.movieapp.database.WatchListShow;
import com.example.movieapp.model.Movie;
import com.example.movieapp.ui.MovieDetails;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context mContext;
    private WatchListDatabase database;

    public MovieAdapter(Context context,List<Movie> movies){
        mContext=context;
        this.movies=movies;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_view,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie=movies.get(position);
        String currentTitle=movie.getTitle();
        holder.title.setText(currentTitle);

        String imageUrl="https://image.tmdb.org/t/p/original";
        imageUrl +=movie.getBackdrop_path();
        Glide.with(mContext).load(imageUrl).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieDetails.class);
                intent.putExtra("movieId",movie.getId());
                mContext.startActivity(intent);
                Toast.makeText(mContext,movie.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        database = WatchListDatabase.getInstance(mContext);
        WatchListShow currentShow = database.showDao().getShowById(movie.getId());
        if(currentShow != null){
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.check);
            holder.addWatchList.setBackground(drawable);
        }
        else{
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.add);
            holder.addWatchList.setBackground(drawable);
        }
        String finalImageUrl = imageUrl;
        holder.addWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the image
                WatchListShow clikedShow = database.showDao().getShowById(movie.getId());
                if(clikedShow != null){
                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.add);
                    holder.addWatchList.setBackground(drawable);
                    database.showDao().deleteTask(clikedShow);
                }
                else{
                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.check);
                    holder.addWatchList.setBackground(drawable);
                    database.showDao().insertTask(new WatchListShow(String.valueOf(movie.getId()),currentTitle,finalImageUrl,movie.getOverview()));
                }



            }
        });

    }



    @Override
    public int getItemCount() {
        if(movies == null)
            return 0;
        return movies.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        Button addWatchList;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.movie_image);
            title=itemView.findViewById(R.id.movie_title);
            addWatchList = itemView.findViewById(R.id.add_watchList);
        }
    }
}
