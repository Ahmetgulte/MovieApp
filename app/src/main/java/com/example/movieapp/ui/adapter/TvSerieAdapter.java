package com.example.movieapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.model.TvSerie;
import com.example.movieapp.ui.TvSerieDetails;

import java.util.List;

public class TvSerieAdapter  extends RecyclerView.Adapter<TvSerieAdapter.TvSerieViewHolder>{
    private List<TvSerie> series;
    private Context mContext;

    public TvSerieAdapter(Context context,List<TvSerie> series){
        mContext=context;
        this.series=series;
    }

    @NonNull
    @Override
    public TvSerieAdapter.TvSerieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_view,parent,false);
        return new TvSerieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvSerieAdapter.TvSerieViewHolder holder, int position) {
        TvSerie serie =series.get(position);
        holder.title.setText(serie.getName());
        String imageUrl="https://image.tmdb.org/t/p/original";
        imageUrl +=serie.getPoster();
        Glide.with(mContext).load(imageUrl).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, TvSerieDetails.class);
                intent.putExtra("serieId",serie.getId());
                mContext.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        if(series == null)
            return 0;
        return series.size();
    }


    class TvSerieViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        public TvSerieViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.movie_image);
            title=itemView.findViewById(R.id.movie_title);
        }
    }
}
