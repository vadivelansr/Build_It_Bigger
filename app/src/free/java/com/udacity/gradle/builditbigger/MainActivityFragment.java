package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.vadivelansr.jokeactivity.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokeReceivedListener {
    @Bind(R.id.adView)
    AdView mAdView;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    InterstitialAd mInterstitialAd;
    String mJoke;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });
        mInterstitialAd.loadAd(getAdRequest());
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        mAdView.loadAd(getAdRequest());
        return root;
    }

    @OnClick(R.id.button_joke)
    public void tellJoke(View view){
        fetchJoke();
    }

    @Override
    public void onReceive(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mJoke = joke;
        if(mInterstitialAd != null && mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else{
            startJokeActivity();
        }
    }
    public AdRequest getAdRequest(){
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }
    public void fetchJoke(){
        mProgressBar.setVisibility(View.VISIBLE);
        new JokeEndpointAsyncTask().execute(this);
    }
    public void startJokeActivity(){
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.INTENT_JOKE, mJoke);
        startActivity(intent);
    }
}
