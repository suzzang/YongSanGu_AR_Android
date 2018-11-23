package com.smtown.yongsangu_ar_project.sookmyung.intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.MainActivity;
import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.sookmyung.ar.ARActivity;

public class ThirdIntroActivity extends AppCompatActivity {
    public String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent1 = new Intent(ThirdIntroActivity.this, com.smtown.yongsangu_ar_project.UnityPlayerActivity.class);
                msg = "FindPark";
                intent1.putExtra("scene",msg);
                startActivity(intent1);
            }
        },3000);
    }
}
