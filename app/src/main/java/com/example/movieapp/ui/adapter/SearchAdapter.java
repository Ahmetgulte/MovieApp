package com.example.movieapp.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.movieapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> searchedWords;
    private LayoutInflater layoutInflater;

    public SearchAdapter(Context context, ArrayList<String> searchedWords) {
        this.context = context;
        this.searchedWords = searchedWords;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return searchedWords.size();
    }

    @Override
    public Object getItem(int position) {
        return searchedWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.searched_item,parent,false);
        TextView searchedWord=view.findViewById(R.id.searched_word);
        searchedWord.setText(searchedWords.get(position));
        TextView delete=view.findViewById(R.id.delete_word);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedWords.remove(position);
                SharedPreferences sharedPreferences= context.getApplicationContext().getSharedPreferences("searched", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(searchedWords);
                editor.putString("searched list",json);
                editor.apply();
                notifyDataSetChanged();
            }
        });
        return view;
    }

}
