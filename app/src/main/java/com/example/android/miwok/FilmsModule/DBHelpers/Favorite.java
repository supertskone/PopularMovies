package com.example.android.miwok.FilmsModule.DBHelpers;

/**
 * Created by EXLRT-Nemanja on 1/24/2017.
 */

public class Favorite {
    private long id;
    private String favorite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return favorite;
    }
}
