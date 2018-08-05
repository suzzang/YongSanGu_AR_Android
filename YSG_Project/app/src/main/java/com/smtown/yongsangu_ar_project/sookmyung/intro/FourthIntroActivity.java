package com.smtown.yongsangu_ar_project.sookmyung.intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smtown.yongsangu_ar_project.MainActivity;
import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.sookmyung.ar.ARActivity;
import com.smtown.yongsangu_ar_project.splash.SplashActivity;

public class FourthIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FourthIntroActivity.this, ARActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
