package com.example.android.miwok.FilmsModule.AsyncHelpers;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Video;

/**
 * Created by Nemanja on 1/7/2017.
 */

public class LoadFilmTrailers extends AsyncTask<Integer, Void, ArrayList<Video>> {

    public LoadFilmTrailers(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void onLoaded(List<Video> filmsList);
        void onError();
    }
    private Listener mListener;

    @Override
    protected ArrayList<Video> doInBackground(Integer... params) {
        String API_KEY = "ede4a0298ebb59cf9f1a83bc9d6968c5";
        TmdbApi tmdbApi = new TmdbApi(API_KEY);
        TmdbMovies movies = tmdbApi.getMovies();
        ArrayList<Video> list = (ArrayList<Video>) movies.getVideos(params[0], "en");
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Video> response) {
        if (response != null) {
            mListener.onLoaded(response);
        } else {
            mListener.onError();
        }
    }
}

