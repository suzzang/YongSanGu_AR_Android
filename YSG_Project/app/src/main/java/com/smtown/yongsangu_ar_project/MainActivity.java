package com.smtown.yongsangu_ar_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.sookmyung.intro.FirstIntroActivity;
import com.smtown.yongsangu_ar_project.splash.SplashActivity;

public class MainActivity extends AppCompatActivity{

    TextView map_sook;
    TextView map_hyo;
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map_sook = findViewById(R.id.map_sook);
        map_hyo = findViewById(R.id.map_hyo);

        map_sook.setOnClickListener(new View.OnClickListener() { //구현중
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent(MainActivity.this, UnityPlayerActivity.class);
//                startActivity(intent1);

                Intent intent1 = new Intent(MainActivity.this, com.smtown.yongsangu_ar_project.UnityPlayerActivity.class);
                msg = "FindPark";
                intent1.putExtra("scene",msg);
                startActivity(intent1);
            }
        });

        map_hyo.setOnClickListener(new View.OnClickListener() { //미구현
            @Override
            public void onClick(View view) {
                //Intent intent1 = new Intent(MainActivity.this, FirstIntroActivity.class);
                //startActivity(intent1);
                Intent intent2 = new Intent(MainActivity.this, com.smtown.yongsangu_ar_project.UnityPlayerActivity.class);
                msg = "HyochangIntro";
                intent2.putExtra("scene",msg);
                startActivity(intent2);
            }
        });



    }

}
