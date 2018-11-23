package com.smtown.yongsangu_ar_project.sookmyung.ending.reward;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.sookmyung.ending.camera.dialog.CameraImageDialog;
import com.smtown.yongsangu_ar_project.sookmyung.ending.reward.dialog.RewardCameraDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RewardActivity extends AppCompatActivity {
    long mNow;
    Date mDate;
    Bitmap bitmap;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        View reward = findViewById(R.id.real_reward_layout);
        TextView r_name = findViewById(R.id.tv_r_name);
        TextView r_depart = findViewById(R.id.tv_r_depart);

        TextView r_date = findViewById(R.id.tv_r_date);

        ImageView btn_camera = findViewById(R.id.btn_smreward_camera);

        Intent intent1 = getIntent();

        String name = intent1.getStringExtra("input_name");
        r_name.setText(name);
        String depart = intent1.getStringExtra("input_depart");
        r_depart.setText(depart);

        r_date.setText(getTime());

        btn_camera.setOnClickListener(view ->{
            bitmap = captureView(reward);
            DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();

            int width_d = displayMetrics.widthPixels;
            int  height_d = displayMetrics.heightPixels;

            RewardCameraDialog rewardCameraDialog = new  RewardCameraDialog(this,bitmap);


            WindowManager.LayoutParams wm =  rewardCameraDialog.getWindow().getAttributes();
            wm.copyFrom( rewardCameraDialog.getWindow().getAttributes());

            rewardCameraDialog.show();
        });



    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    public static Bitmap captureView(View View) {
        View.buildDrawingCache();
        Bitmap captureView = View.getDrawingCache();
        return captureView;
    }




}
