package com.example.android.miwok.FilmsModule.AsyncHelpers;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Nemanja on 1/7/2017.
 */

public class LoadTopRatedFilms extends AsyncTask<String, Void, ArrayList<MovieDb>> {

    public LoadTopRatedFilms(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void onLoaded(List<MovieDb> filmsList);
        void onError();
    }

    private Listener mListener;

    @Override
    protected ArrayList<MovieDb> doInBackground(String... strings) {
        try {

        String[] x = strings;
        String API_KEY = "ede4a0298ebb59cf9f1a83bc9d6968c5";
        TmdbApi tmdbApi = new TmdbApi(API_KEY);
        TmdbMovies movies = tmdbApi.getMovies();
        MovieResultsPage moviez = movies.getTopRatedMovies("en", 1);
        ArrayList<MovieDb> fList = (ArrayList<MovieDb>) moviez.getResults();
            return fList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDb> response) {
        if (response != null) {
            mListener.onLoaded(response);
        } else {
            mListener.onError();
        }
    }
}