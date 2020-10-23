package com.india.DELETEit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Process;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class NoAppfound extends AppCompatActivity {
    Animation animFadeIn;
    TextView text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_appfound);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        text = (TextView) findViewById(R.id.textView5);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeinanim);

        text.startAnimation(animFadeIn);

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killApplicationProcess();
    }
    private void killApplicationProcess(){
        Process.killProcess(Process.myPid());
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }





}
