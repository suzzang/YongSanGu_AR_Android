package com.smtown.yongsangu_ar_project.sookmyung.intro;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;

public class FirstIntroActivity extends AppCompatActivity {
    ImageView next1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_intro);
        next1 = findViewById(R.id.btn_next_firstintro);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstIntroActivity.this,SecondIntroActivity.class);
                startActivity(intent);
            }
        });
    }
}
