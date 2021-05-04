package com.example.movieapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.movieapp.ui.MovieList;
import com.example.movieapp.R;
import com.example.movieapp.ui.adapter.SearchAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SearchFragment extends Fragment {
    SearchView searchView;
    ListView listView;
    ArrayList<String> searchedWords;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        listView = view.findViewById(R.id.searched_shows);
        searchView = view.findViewById(R.id.search_view);
        loadSearchedWords();
        SearchAdapter adapter = new SearchAdapter(getContext(),searchedWords);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchedWords.add(query);
                adapter.notifyDataSetChanged();
                saveToTheList();
                Intent intent = new Intent(getContext(), MovieList.class);
                intent.putExtra("media_type","movie");
                intent.putExtra("type","search");
                intent.putExtra("query",query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return view;
    }

    public void saveToTheList(){
        SharedPreferences sharedPreferences= getActivity().getApplicationContext().getSharedPreferences("searched", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(searchedWords);
        editor.putString("searched list",json);
        editor.apply();
    }

    private void loadSearchedWords() {
        SharedPreferences sharedPreferences= getActivity().getApplicationContext().getSharedPreferences("searched", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("searched list",null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        searchedWords = gson.fromJson(json,type);
        if(searchedWords == null){
            searchedWords = new ArrayList<>();
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
