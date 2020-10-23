package com.india.DELETEit;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    Animation animFadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeinanim);
        TextView title = (TextView) findViewById(R.id.title);
        TextView dhaga = (TextView) findViewById(R.id.textView4);
        TextView india = (TextView) findViewById(R.id.india);
        title.startAnimation(animFadeIn);
        dhaga.startAnimation(animFadeIn);
        india.startAnimation(animFadeIn);


        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(2*1000);


                    Intent i=new Intent(getBaseContext(), AppSelection.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
}
