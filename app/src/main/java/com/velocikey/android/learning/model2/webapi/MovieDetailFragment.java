package com.velocikey.android.learning.model2.webapi;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.velocikey.android.learning.model2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {
    // Class fields
    private static final String LOG_TAG = MovieDetailFragment.class.getSimpleName();

    private static final String ARG_ID = "movie_id";
    private static final String ARG_title = "title";
    private static final String ARG_PosterPath = "posterPath";
    private static final String ARG_Popularity = "popularity";
    private static final String ARG_Rating = "rating";

    // Object fields

    private int mId;
    private String mTitle;
    private String mPosterPath;
    private float mPopularity;
    private float mRating;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param movieId    id of the movie (from TMDB)
     * @param title      Title of the movie
     * @param posterPath the full path to a poster image for the movie
     * @param popularity user popularity value
     * @param rating     critic rating
     * @return A new instance of fragment MovieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(int movieId, String title, String posterPath, float popularity, float rating) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, movieId);
        args.putString(ARG_title, title);
        args.putString(ARG_PosterPath, posterPath);
        args.putFloat(ARG_Popularity, popularity);
        args.putFloat(ARG_Rating, rating);
        fragment.setArguments(args);
        return fragment;
    }

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(ARG_ID);
            mTitle = getArguments().getString(ARG_title);
            mPosterPath = getArguments().getString(ARG_PosterPath);
            mPopularity = getArguments().getFloat(ARG_Popularity);
            mRating = getArguments().getFloat(ARG_Rating);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

}
