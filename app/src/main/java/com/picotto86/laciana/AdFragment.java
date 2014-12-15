package com.picotto86.laciana;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.*;

public class AdFragment extends Fragment {
    private AdView mAdView;

    public AdFragment() {
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) getView().findViewById(R.id.adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ad, container, false);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        mAdView.destroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        mAdView.resume();
        super.onResume();


    }


    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        mAdView.pause();
        super.onPause();


    }
}