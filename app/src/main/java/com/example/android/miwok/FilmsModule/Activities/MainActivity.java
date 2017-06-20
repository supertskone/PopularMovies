/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version com.example.android.miwok.2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok.FilmsModule.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.android.miwok.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar=null;
    private String[] category=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        category = getResources().getStringArray(R.array.category);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.category, R.layout.spinner_dropdown_item);
        Spinner navigationSpinner = new Spinner(getSupportActionBar().getThemedContext());
        navigationSpinner.setAdapter(spinnerAdapter);
        toolbar.addView(navigationSpinner, 0);

        navigationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    Toast.makeText(MainActivity.this, "You selected: " + category[position], Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    startPopular();
                }
                if (position == 2) {
                    startTopRated();
                }
                if (position == 3) {
                    startFavorites();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void startPopular(){
        Intent intent = new Intent(MainActivity.this, FilmsActivity.class);
        intent.putExtra("choice", "Popular");
        startActivity(intent);
    }

    public void startTopRated(){
        Intent intent = new Intent(MainActivity.this, FilmsActivity.class);
        intent.putExtra("choice", "Top Rated");
        startActivity(intent);
    }

    public void startFavorites(){
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
