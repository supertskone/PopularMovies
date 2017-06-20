package com.example.android.miwok.FilmsModule.Adapters;

import android.app.Activity;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.miwok.R;

import java.util.List;

import info.movito.themoviedbapi.model.Reviews;

/**
 * Created by EXLRT-Nemanja on 1/24/2017.
 */
public class ReviewsAdapter extends ArrayAdapter {
    private Context context;
    private List<Reviews> reviews;

    public ReviewsAdapter(Context context, List<Reviews> reviewz) {
        super(context, R.layout.list_item_reviews, reviewz);
        this.context = context;
        this.reviews = reviewz;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_reviews, parent, false);
        }
        String review = reviews.get(position).getContent();
        TextView textView = (TextView) convertView.findViewById(R.id.review_link);
        textView.setText(review);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return convertView;
    }
}

