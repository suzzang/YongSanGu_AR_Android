package com.smtown.yongsangu_ar_project.sookmyung.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;

public class SecondIntroActivity extends AppCompatActivity {
    TextView next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_intro);
        next2 = findViewById(R.id.btn_next_secondintro);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondIntroActivity.this,ThirdIntroActivity.class);
                startActivity(intent);
            }
        });
    }
}
