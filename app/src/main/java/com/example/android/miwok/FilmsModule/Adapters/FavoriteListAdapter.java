package com.example.android.miwok.FilmsModule.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

/**
 * Created by EXLRT-Nemanja on 1/19/2017.
 */

public class FavoriteListAdapter extends ArrayAdapter {


    private Context context;
    private List<String> trailers;

    public FavoriteListAdapter(Context context, List<String> trailerz) {

        super(context, R.layout.activity_favorites, trailerz);
        try {
            this.context = context;
            this.trailers = trailerz;
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.content_favorites, parent, false);
        }
        // Displaying a textview
        TextView textView = (TextView) convertView.findViewById(R.id.favorite);
        textView.setText(trailers.get(position));

        return convertView;
    }
}

