package com.example.android.miwok.FilmsModule.Adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

import info.movito.themoviedbapi.model.Video;

/**
 * Created by EXLRT-Nemanja on 1/19/2017.
 */

public class TrailerListAdapter extends ArrayAdapter {
    private Context context;
    private List<Video> trailers;

    public TrailerListAdapter(Context context, List<Video> trailerz) {
        super(context, R.layout.list_item_trailers, trailerz);
        this.context = context;
        this.trailers = trailerz;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_trailers, parent, false);
           }
            String trailer = trailers.get(position).getKey();
            TextView textView = (TextView) convertView.findViewById(R.id.trailer_link);
            textView.setText(Html.fromHtml("<a href=https://www.youtube.com/watch?v="+trailer+"> Trailer " + position));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            return convertView;
    }

    @Override
    public Video getItem(int position) {
        return trailers.get(position);
    }
}
