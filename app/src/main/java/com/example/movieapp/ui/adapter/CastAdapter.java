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
import com.example.movieapp.model.Cast;

import java.util.ArrayList;


public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Cast> casts;
    public CastAdapter(Context context,ArrayList<Cast> casts){
        this.context=context;
        this.casts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cast_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast currenCast=casts.get(position);
        String imageUrl="https://image.tmdb.org/t/p/original";
        imageUrl +=currenCast.getProfile_path();
        Glide.with(context).load(imageUrl).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.imageView);

        holder.castName.setText(currenCast.getName());
        holder.charName.setText(currenCast.getCharacter());
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView castName;
        TextView charName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cast_image);
            castName=itemView.findViewById(R.id.cast_name);
            charName=itemView.findViewById(R.id.cast_char_name);
        }
    }

}
