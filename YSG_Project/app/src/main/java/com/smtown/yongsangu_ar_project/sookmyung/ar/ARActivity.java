package com.smtown.yongsangu_ar_project.sookmyung.ar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.sookmyung.ending.camera.CameraTestActivity;

public class ARActivity extends AppCompatActivity {
    TextView cameratest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        cameratest=findViewById(R.id.btn_cameratest);


        cameratest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ARActivity.this, CameraTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
