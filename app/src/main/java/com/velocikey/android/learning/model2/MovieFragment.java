package com.velocikey.android.learning.model2;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.velocikey.android.learning.model2.webapi.tmdb.Movie;
import com.velocikey.android.learning.model2.webapi.tmdb.WebApiTMDB;

import java.util.ArrayList;
import java.util.Vector;

/**
 * A fragment that handles display of a list of movies (showing their posters)
 */
public class MovieFragment extends Fragment {
    // Class fields
    private static final String LOG_TAG = MovieFragment.class.getSimpleName();

    private static final int MOVIE_QUERY_COUNT = 20;
    // Object fields
    /**
     * Movie list loaded by the asynchronous task
     */
    private ArrayList<Movie> mMovieList = null;
    private MovieAdapter movieAdapter;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;


    public MovieFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO add setting menu
        setHasOptionsMenu(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;

        int id = item.getItemId();
        Log.v(LOG_TAG, "onOptionsItemSelected, with id = " + id);
        //TODO add menu items
        switch (id) {
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String defaultOrder = "Popularity";

        FetchMovieInfoTask movieInfoTask = new FetchMovieInfoTask();
        movieInfoTask.execute(defaultOrder);
        // Establish the mmove adapter
        Log.v(LOG_TAG, "About to dreate a MovieAdapter");
        movieAdapter = new MovieAdapter(getActivity(), mMovieList);

        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.movie_list_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setOnClickListener(new RecyclerView.OnClickListener() {

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.v(LOG_TAG , "onAttach now");
        if (!(activity instanceof OnFragmentInteractionListener)) {
            Log.e(LOG_TAG, "activity is not an instance of MovieFragmentCallback");
        }
        //TODO what else needs to be done at initial attach?
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    // Asynchronous class definitions
    // Asynchronous task class definition for Movies
    public class FetchMovieInfoTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected ArrayList<Movie> doInBackground(String... params) {

            WebApiTMDB tmdb = new WebApiTMDB();
            Log.v(LOG_TAG, "about to get movies");
            //String result = tmdb.getRawMovie("sort_by=popularity.desc");
            String sortByValue = "popularity.desc";
            if (params.length > 0) {
                //TODO handle these strings in preferences
                if (params[0].equalsIgnoreCase("Popularity")) {
                    sortByValue = "popularity.desc";
                } else if (params[0].equalsIgnoreCase("User Rating")) {
                    sortByValue = "vote_average.desc";
                }
            }
            //TODO consider going directly to ArrayList
            Vector<Movie> parsedResult = tmdb.getMovieInfo(sortByValue, MOVIE_QUERY_COUNT);

            Log.v(LOG_TAG, "back from movie request");

            //TODO consider putting into database here?
            ArrayList<Movie> movieInfo = new ArrayList<Movie>(parsedResult.size());
            for (int i = 0; i < parsedResult.size(); i++) {
                movieInfo.add(parsedResult.get(i));
            }
            Log.v(LOG_TAG, "finished moving to ArrayList");
            for (Movie movie : movieInfo) {
                Log.v(LOG_TAG, movie.toString());
            }
            //TODO move elsewhere


            return movieInfo;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> result) {
            Log.v(LOG_TAG, "Finished movie acquisition (" + result.size() + " entries");
            mMovieList = result;
            Log.v(LOG_TAG, "Now mMovieList size is " + mMovieList.size());
            // handle putting posters into the movie adapter view
            movieAdapter.notifyItemRangeChanged(0, mMovieList.size());

        }
    }
}