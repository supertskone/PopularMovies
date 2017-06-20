package com.example.android.miwok.FilmsModule.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.miwok.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by EXLRT-Nemanja on 1/19/2017.
 */

public class ImageListAdapter extends ArrayAdapter {


    private Context context;
    private List<String> imageUrls;

    public ImageListAdapter(Context context, List<String> imageUrls) {
        super(context, R.layout.list_item_films, imageUrls);
        this.context = context;
        this.imageUrls = imageUrls;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_films, parent, false);
        }
        try {
            Picasso
                    .with(context)
                    .load(imageUrls.get(position))
                    .into((ImageView) convertView);
        }
        catch (Exception e) {
        }
        return convertView;
    }
}
