package com.example.movieapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.database.WatchListDatabase;
import com.example.movieapp.database.WatchListShow;

import java.util.List;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.ShowViewHolder> {
    private List<WatchListShow> shows;
    private Context mContext;
    private WatchListDatabase database;

    public WatchListAdapter(Context context,List<WatchListShow> movies){
        mContext=context;
        this.shows=movies;

    }

    @NonNull
    @Override
    public WatchListAdapter.ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.watchlist_item,parent,false);
        return new WatchListAdapter.ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListAdapter.ShowViewHolder holder, int position) {
        WatchListShow show=shows.get(position);
        String currentTitle=show.getName();
        holder.title.setText(currentTitle);
        holder.description.setText(show.getDescription());
        String imageUrl="https://image.tmdb.org/t/p/original";
        imageUrl +=show.getPoster();
        Glide.with(mContext).load(imageUrl).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.image);

    }



    @Override
    public int getItemCount() {
        if(shows == null)
            return 0;
        return shows.size();
    }


    class ShowViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView description;
        public ShowViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.watchlist_poster);
            title=itemView.findViewById(R.id.watchlist_title);
            description=itemView.findViewById(R.id.watchlist_description);

        }
    }
}
