package com.velocikey.android.learning.model2.webapi.tmdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Joseph White on 2015 Jul 13.
 */
public class Movie {
    // Class fields
    private static final String LOG_TAG = Movie.class.getSimpleName();
    //TODO see if to string a linkg with fragments
    public static final String PARAM_ID = "id";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_RELEASEDATE = "releaseDate";
    public static final String PARAM_POPULARITY = "popularity";
    public static final String PARAM_RATING = "rating";
    public static final String PARAM_POSTERPATH = "posterPath";
    // Object fields
    private int id;
    private String title;
    private String releaseDate;
    private float popularity;
    private float rating;
    private String posterPath;

    public Movie(int id, String title, String releaseDate, float popularity, float rating, String posterPath) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.rating = rating;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public float getPopularity() {
        return popularity;
    }
    public float getRating() {
        return rating;
    }

    /** Obtain the full image URL for the posterPath
     *
     * @return
     */
    public String getPosterPath() {
        return WebApiTMDB.getImageURL(posterPath, 1);
    }

    //Put entry into database
    public long insertDBEntry(SQLiteDatabase db) {
        long newRowID;
        ContentValues values = makeContentValues(true);

        newRowID = db.insert(MovieContract.movie.PATH, null, values);
        return newRowID;
    }
    public long replaceAddDBEntry(SQLiteDatabase db) {
        long rowID;
        String[] projection = {MovieContract.movie._ID};
        String sortOrder = null;
        String selection = MovieContract.movie._ID + "= '%s'";
        String[] selectionArgs = {new Integer(id).toString()};

        Cursor c = db.query(MovieContract.movie.PATH,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (c.getCount() > 0) {
            // update the record
            ContentValues values = makeContentValues(false);

            rowID = db.update(MovieContract.movie.PATH, values, selection, selectionArgs);
        } else {
            rowID = insertDBEntry(db);
        }
        return rowID;
    }

    /** Convenience method for making values to be inserted or updated in to the database
     *
     * @param insert true if a db.insert(...) operation will be called with the resultant values.
     * @return
     */
    private ContentValues makeContentValues(boolean insert) {
        ContentValues values = new ContentValues();
        if (insert) {
            values.put(MovieContract.movie._ID, id);
        }
        values.put(MovieContract.movie.TITLE, title);
        values.put(MovieContract.movie.RELEASEDATE, releaseDate);
        values.put(MovieContract.movie.POPULARITY, popularity);
        values.put(MovieContract.movie.RATING, rating);
        values.put(MovieContract.movie.POSTERPATH, posterPath);
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.popularity, popularity) != 0) return false;
        if (Float.compare(movie.rating, rating) != 0) return false;
        if (!title.equals(movie.title)) return false;
        if (!releaseDate.equals(movie.releaseDate)) return false;
        return posterPath.equals(movie.posterPath);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + (popularity != +0.0f ? Float.floatToIntBits(popularity) : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + posterPath.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "" + id + "|" + title + "|" + releaseDate + "|" + popularity + "|" + rating + "|" + posterPath;
    }

}