package com.example.android.miwok.FilmsModule.Activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.android.miwok.FilmsModule.Adapters.ImageListAdapter;
import com.example.android.miwok.FilmsModule.AsyncHelpers.LoadPopularFilms;
import com.example.android.miwok.FilmsModule.AsyncHelpers.LoadTopRatedFilms;
import com.example.android.miwok.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;

public class FilmsActivity extends AppCompatActivity implements LoadPopularFilms.Listener, LoadTopRatedFilms.Listener, AdapterView.OnItemClickListener {

    private GridView mListView;
    private String toolbarTitle = null;
    private String[] category = null;

    private String URL = "https://www.xyz.com";
    private List<HashMap<String, String>> mFilmsMapList = new ArrayList<>();
    private static final String KEY_ID = "id";
    private static final String KEY_TITL = "title";
    private static final String KEY_THUM = "thumbnail";
    private static final String KEY_LENG = "runtime";
    private static final String KEY_OVER = "overview";
    private static final String KEY_VOTE = "vote";
    private static final String KEY_YEAR = "year";
    private static final String KEY_CHOI = "choice";
    private static final String KEY_REVI = "reviews";

    private List<String> imageUrls = new ArrayList<String>();
    private String choice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();

            choice = (String) bundle.getString("choice");
        }

        mFilmsMapList.clear();
        mListView = (GridView) findViewById(R.id.list_view_films);
        mListView.setOnItemClickListener(this);
        if (choice.equals("Top Rated")) {
            new LoadTopRatedFilms(this).execute(URL);
            toolbarTitle = "Top Rated";
        }
        if (choice.equals("Popular")) {
            new LoadPopularFilms(this).execute(URL);
            toolbarTitle = "Popular";
        }
    }

    @Override
    public void onLoaded(List<info.movito.themoviedbapi.model.MovieDb> filmsList) {
        for (MovieDb results : filmsList) {
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_ID, String.valueOf(results.getId()));
            map.put(KEY_TITL, results.getTitle());
            map.put(KEY_THUM, "https://image.tmdb.org/t/p/w300" + results.getPosterPath());
            map.put(KEY_OVER, results.getOverview());
            map.put(KEY_LENG, String.valueOf(results.getRuntime()));
            map.put(KEY_VOTE, String.valueOf(results.getVoteAverage()));
            map.put(KEY_YEAR, results.getReleaseDate());
            map.put(KEY_CHOI, toolbarTitle);

            imageUrls.add("https://image.tmdb.org/t/p/w300" + results.getPosterPath());

            mFilmsMapList.add(map);
        }
        if (mFilmsMapList.size() > 1) {
            loadListView();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String message = "";
        try {


            //new LoadPopularFilms(this);

            String id = mFilmsMapList.get(i).get("id");
            String title = mFilmsMapList.get(i).get("title");
            String thumbnail = mFilmsMapList.get(i).get("thumbnail");
            String overview = mFilmsMapList.get(i).get("overview");
            String length = mFilmsMapList.get(i).get("runtime");
            String vote_average = mFilmsMapList.get(i).get("vote");
            String year = mFilmsMapList.get(i).get("year");
            String choice = mFilmsMapList.get(i).get("choice");

            Intent intent = new Intent(FilmsActivity.this, DetailsActivity.class);
            intent.putExtra("id", id.toString());
            intent.putExtra("title", title.toString());
            intent.putExtra("thumbnail", thumbnail.toString());
            intent.putExtra("overview", overview.toString());
            intent.putExtra("length", length.toString());
            intent.putExtra("vote_average", vote_average.toString());
            intent.putExtra("release", year);
            intent.putExtra("choice", choice);
            startActivity(intent);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void loadListView() {
        ListAdapter adapter = new ImageListAdapter(FilmsActivity.this, imageUrls);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent filmsIntent = new Intent(FilmsActivity.this, MainActivity.class);
        startActivity(filmsIntent);
    }
}