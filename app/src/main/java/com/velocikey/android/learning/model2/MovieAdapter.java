package com.velocikey.android.learning.model2;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.velocikey.android.learning.model2.webapi.tmdb.Movie;

import java.util.ArrayList;

/**
 * Created by Joseph White on 31-Jul-2015
 *
 * @since 1.0
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    // Class fields
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();
    // Object fields
    private Context mContext;
    private ArrayList<Movie> mMovie;
    private MovieAdapter.MovieViewHolder mMovieViewHolder;


    /** Construct the MovieAdapter with provided data source and context.
     *
     * @param context
     * @param movie data for the adapter to use
     */
    public MovieAdapter(Context context, ArrayList<Movie> movie) {
        mContext = context;
        mMovie = movie;
        if (movie == null) {
            Log.v(LOG_TAG, "null movie on MovieAdapter creation");
        } else {
            Log.v(LOG_TAG, "Created MovieAdapter (entry count = " + mMovie.size() + ")");
        }
    }



    /**
     * Called when RecyclerView needs a new {@link MovieViewHolder} of the given type to represent
     * an item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(MovieViewHolder, int)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(MovieViewHolder, int)
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movie, parent, false);
        mMovieViewHolder = new MovieViewHolder(v);
        return mMovieViewHolder;
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * should update the contents of the {@link RecyclerView.ViewHolder#itemView} to reflect the item at
     * the given position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this
     * method again if the position of the item changes in the data set unless the item itself
     * is invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside this
     * method and should not keep a copy of it. If you need the position of an item later on
     * (e.g. in a click listener), use {@link RecyclerView.ViewHolder#getAdapterPosition()} which will have
     * the updated adapter position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        String posterPath;

        posterPath = mMovie.get(position).getPosterPath();
        Log.v(LOG_TAG, "Loading poster from " + posterPath);
        Uri uri = Uri.parse(posterPath);
        Picasso.with(mContext).load(uri).into(mMovieViewHolder.poster);
        //TODO add error and placeholder drawables
        //posterOnClickListner = new MovieAdapter.posterOnClickListener();

    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (mMovie == null) {
            return 0;
        } else {
            return mMovie.size();
        }
    }
    // Object Fields


    //-------------------------- inner classes
    // Viewholder definitions for the RecyclerView
    public class MovieViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        //TODO change to protected?
        public ImageView poster;

        public MovieViewHolder(View v) {
            super(v);
            poster = (ImageView) v.findViewById(R.id.imageposter);
            Log.v(LOG_TAG, "Need to set ViewHolder");
        }
    }

    public class posterOnClickListener implements View.OnClickListener {
        // Class fields
        private final String LOG_TAG = posterOnClickListener.class.getSimpleName();
        public void onClick(View v) {
            //TODO how to get the position of this View??
            int position = mMovieViewHolder.getAdapterPosition();
            Log.e(LOG_TAG, "Clicked on " + position);
            //TODO now use callback
            //mMessageClickedHandler.onItemClick(parent?, v, position, );
        }
    }
}
