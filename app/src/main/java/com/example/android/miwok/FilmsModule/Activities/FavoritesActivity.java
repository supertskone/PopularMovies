package com.example.android.miwok.FilmsModule.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.android.miwok.FilmsModule.Adapters.FavoriteListAdapter;
import com.example.android.miwok.FilmsModule.DBHelpers.Favorite;
import com.example.android.miwok.FilmsModule.DBHelpers.FavoriteDataSource;
import com.example.android.miwok.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    ListView favoriteList;
    private FavoriteDataSource datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        favoriteList = (ListView) findViewById(R.id.details_favorites);
        datasource = new FavoriteDataSource(this, null, null, 2);
        datasource.open();
        List<Favorite> favorites = datasource.getAllFavorites();
        List<String> fmovies = new ArrayList<>();
        for(int i = 0; i < favorites.size(); i++) {
            fmovies.add(datasource.getAllFavorites().get(i).getFavorite());
        }
        ListAdapter adapter = new FavoriteListAdapter(FavoritesActivity.this, fmovies);
        favoriteList.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent filmsIntent = new Intent(FavoritesActivity.this, MainActivity.class);
        startActivity(filmsIntent);
    }
}
