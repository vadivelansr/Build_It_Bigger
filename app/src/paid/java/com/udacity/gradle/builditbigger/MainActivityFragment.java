package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.vadivelansr.jokeactivity.JokeActivity;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokeReceivedListener {
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.button_joke)
    public void tellJoke(View view) {
        fetchJoke();
    }

    @Override
    public void onReceive(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mJoke = joke;
        startJokeActivity();

    }


    public void fetchJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        new JokeEndpointAsyncTask().execute(this);
    }

    public void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.INTENT_JOKE, mJoke);
        startActivity(intent);
    }
}
