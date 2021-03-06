package com.picotto86.laciana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.ads.*;


import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends Activity {
    // How long in milliseconds to wait for the interstitial to load.
    private static final int WAIT_TIME = 5000;

    // Your interstitial ad unit ID.
    private static final String AD_UNIT_ID = "ca-app-pub-8190137666568349/6254117915";

    public InterstitialAd interstitial;
    private Timer waitTimer;
    private AdRequest adRequest;
    private boolean interstitialCanceled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);


        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(AD_UNIT_ID);
        adRequest=new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // If the interstitial was canceled due to a timeout or an app being sent to the background,
                // don't show the interstitial.
                if (!interstitialCanceled) {
                    waitTimer.cancel();
                    displayInterstitial();
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The interstitial failed to load. Start the application.
                startMainActivity();
            }
        });


        waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                interstitialCanceled = true;
                SplashScreenActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // The interstitial didn't load in a reasonable amount of time. Stop waiting for the
                        // interstitial, and start the application.
                        startMainActivity();
                    }
                });
            }
        }, WAIT_TIME);
    }

    @Override
    public void onPause() {
        // Flip the interstitialCanceled flag so that when the user comes back they aren't stuck inside
        // the splash screen activity.
        waitTimer.cancel();
        interstitialCanceled = true;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (interstitial.isLoaded()) {
            // The interstitial finished loading while the app was in the background. It's up to you what
            // the behavior should be when they return. In this example, we show the interstitial since
            // it's ready.
            interstitial.show();
        } else if (interstitialCanceled) {
            // There are two ways the user could get here:
            //
            // 1. After dismissing the interstitial
            // 2. Pressing home and returning after the interstitial finished loading.
            //
            // In either case, it's awkward to leave them in the splash screen activity, so just start the
            // application.
            startMainActivity();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub

        super.onDestroy();
    }

    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }



    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}