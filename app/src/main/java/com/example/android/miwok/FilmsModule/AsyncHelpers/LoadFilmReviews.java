package com.example.android.miwok.FilmsModule.AsyncHelpers;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbReviews;
import info.movito.themoviedbapi.model.Reviews;

/**
 * Created by Nemanja on 1/7/2017.
 */


public class LoadFilmReviews extends AsyncTask<Integer, Void, ArrayList<Reviews>> {

    public LoadFilmReviews(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void onLoadedReview(List<Reviews> filmsList);
        void onError();
    }
    private Listener mListener;

    @Override
    protected ArrayList<Reviews> doInBackground(Integer... params) {
        String API_KEY = "ede4a0298ebb59cf9f1a83bc9d6968c5";
        TmdbApi tmdbApi = new TmdbApi(API_KEY);
        TmdbReviews movies = tmdbApi.getReviews();
        ArrayList<Reviews> list = (ArrayList<Reviews>) movies.getReviews(params[0], "en", 1).getResults();
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Reviews> response) {
        if (response != null) {
            mListener.onLoadedReview(response);
        } else {
            mListener.onError();
        }
    }
}

