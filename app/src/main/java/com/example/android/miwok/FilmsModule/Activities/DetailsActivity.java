package com.example.android.miwok.FilmsModule.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.miwok.FilmsModule.Adapters.ReviewsAdapter;
import com.example.android.miwok.FilmsModule.Adapters.TrailerListAdapter;
import com.example.android.miwok.FilmsModule.AsyncHelpers.LoadFilmReviews;
import com.example.android.miwok.FilmsModule.AsyncHelpers.LoadFilmTrailers;
import com.example.android.miwok.FilmsModule.DBHelpers.Favorite;
import com.example.android.miwok.FilmsModule.DBHelpers.FavoriteDataSource;
import com.example.android.miwok.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import info.movito.themoviedbapi.model.Reviews;
import info.movito.themoviedbapi.model.Video;

public class DetailsActivity extends AppCompatActivity implements LoadFilmTrailers.Listener, LoadFilmReviews.Listener{
    private String title = "";
    private int id;
    private FavoriteDataSource datasource;
    private String type = "";
    ListView trailerList;
    ListView reviewList;
    private TextView switchStatus;
    private Switch mySwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Context context = getApplicationContext();
        Bundle bundle = getIntent().getExtras();

        trailerList = (ListView)findViewById(R.id.details_trailer);
        reviewList = (ListView)findViewById(R.id.details_review);

        id = Integer.parseInt(bundle.getString("id"));
        new LoadFilmTrailers(this).execute(id);

        new LoadFilmReviews(this).execute(id);
        title = bundle.getString("title");

        String thumbnail = bundle.getString("thumbnail");

        //replace image size for better visibility in details window
        thumbnail = thumbnail.replace("w90", "w600");

        String overview = bundle.getString("overview");

        String year = bundle.getString("release");

        String vote_average = bundle.getString("vote_average");

        type = bundle.getString("choice");

        TextView txtView = (TextView) findViewById(R.id.details_title);
        txtView.setText(title);

        TextView overView = (TextView) findViewById(R.id.details_overview);
        overView.setText(overview);

        TextView yearView = (TextView) findViewById(R.id.details_year);
        yearView.setText(year);

        TextView voteView = (TextView) findViewById(R.id.details_rating);
        voteView.setText(vote_average + "/10");

        ImageView imageView = (ImageView) findViewById(R.id.details_thumbnail);
        Picasso
                .with(context)
                .load(thumbnail)
                .into(imageView);

        //////////////////////////////

        datasource = new FavoriteDataSource(this, null, null, 2);
        datasource.open();

        final List<Favorite> values = datasource.getAllFavorites();


        switchStatus = (TextView) findViewById(R.id.switchStatus);
        mySwitch = (Switch) findViewById(R.id.mySwitch);

        //set the switch to ON
        if(datasource.isFavorite(title)) {
            mySwitch.setChecked(true);
        }
        else{
            mySwitch.setChecked(false);
        }
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Favorite favorite = null;

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked) {
                    switchStatus.setText("Switch is currently ON");
                    String x = title;
                    datasource.createFavorite(title);

                }
                else{
                    switchStatus.setText("Switch is currently OFF");
                    //datasource.deleteFavorite(title);
                    datasource.deleteFav(title);
        }
        List<Favorite> p = values;
        //check the current state before we display the screen
        if(mySwitch.isChecked()){
            switchStatus.setText("Switch is currently ON");
        }
        else {
            switchStatus.setText("Switch is currently OFF");
        }

            }
        });

    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        Intent filmsIntent = new Intent(DetailsActivity.this, FilmsActivity.class);
        filmsIntent.putExtra("choice", type);
        startActivity(filmsIntent);
    }

    @Override
    public void onLoaded(List<Video> videosList) {
        List<Video> videos = videosList;
        for(Video video :videosList){
            video.getKey();
        }
        loadListView(videos);
    }

    @Override
    public void onLoadedReview(List<Reviews> filmsList) {
        List<Reviews> videos = filmsList;
        for(Reviews video :filmsList){
            video.getContent();
        }
        loadListReView(videos);
    }

    @Override
    public void onError() {

    }

    private void loadListView(List<Video> videos) {
        ListAdapter adapter = new TrailerListAdapter(DetailsActivity.this, videos);
        trailerList.setAdapter(adapter);
    }

    private void loadListReView(List<Reviews> reviews) {
        ListAdapter adapter = new ReviewsAdapter(DetailsActivity.this, reviews);
        reviewList.setAdapter(adapter);
    }

}
