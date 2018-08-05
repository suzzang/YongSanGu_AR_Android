package com.smtown.yongsangu_ar_project.sookmyung.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;

public class ThirdIntroActivity extends AppCompatActivity {
    TextView next3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_intro);
        next3 = findViewById(R.id.btn_next_thirdintro);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdIntroActivity.this,FourthIntroActivity.class);
                startActivity(intent);
            }
        });
    }
}
