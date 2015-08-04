package com.velocikey.android.learning.model2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.velocikey.android.learning.model2.webapi.MovieDetailFragment;

public class MainActivity extends Activity
        implements MovieFragment.OnFragmentInteractionListener,
                   MovieDetailFragment.OnFragmentInteractionListener {
    // Class fields
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Object fields
    /**
     * Do we have a two pane display (i.e. is app running on a large screen device)?
     */
    private boolean mTwoPane = false;
    /**
     * Main movie list fragment (pane #1)
     */
    private MovieFragment movieFragment;
    /**
     * Movie detail fragment (new pane or pane #2)
     */
    private MovieDetailFragment movieDetailFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "in onCreate");
        setContentView(R.layout.activity_main);
        Log.v(LOG_TAG, "after setContentView");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.v(LOG_TAG, "In onFragmentInteraction");
        //TODO complete access
    }
}
