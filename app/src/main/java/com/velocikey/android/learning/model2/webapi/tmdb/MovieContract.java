package com.velocikey.android.learning.model2.webapi.tmdb;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.velocikey.android.learning.model2.webapi.util.TableCreateDefinition;

/**
 * Created by Joseph White on 2015 Jul 16.
 */
public class MovieContract {

    public static final String AUTHORITY = "com.velocikey.android.learning.movieworld";
    public static final String DBNAME = "movieinfo";
    //TODO make the following two fields private?
    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    protected static final int VERSION = 1 ;

    // Keep folks from accidentally instantiating the class
    private MovieContract() {};

    // Table definitions

    public static final class movie implements BaseColumns {
        // Movie information
        public static final String PATH = "movie";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "?" + PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/" + AUTHORITY + "?" + PATH;

        // Columns
        // TODO (make _id the movide id?
        public static final String _ID = BaseColumns._ID; // use movieid from THDB when storing
        public static final String TITLE = "title";
        public static final String RELEASEDATE = "releasedate";
        public static final String POPULARITY = "popularity";
        public static final String RATING = "rating";
        public static final String POSTERPATH = "posterpath";

        private static final String COLUMNS[] = {
                BaseColumns._ID + " INTEGER PRIMARY KEY",
                TITLE + " TEXT",
                RELEASEDATE + " TEXT",
                POPULARITY + " FLOAT",
                RATING + " FLOAT",
                POSTERPATH + "TEXT"
        };
        public static final String CREATE_TABLE = TableCreateDefinition.getCreateTableString(PATH, COLUMNS);
    }
}