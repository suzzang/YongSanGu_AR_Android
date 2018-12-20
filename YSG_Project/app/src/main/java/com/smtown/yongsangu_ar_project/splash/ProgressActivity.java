package com.smtown.yongsangu_ar_project.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.smtown.bgm.Game_Main_Bgm;
import com.smtown.yongsangu_ar_project.MainActivity;
import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.UnityPlayerActivity;

public class ProgressActivity extends AppCompatActivity {
    private static int progress_percent;
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        //bgm
        startService(new Intent(ProgressActivity.this,Game_Main_Bgm.class));
        //
        progress_percent = 0;

        final Intent intent = new Intent(ProgressActivity.this, MainActivity.class);
//        final Intent intent = new Intent(ProgressActivity.this, UnityPlayerActivity.class);
       // msg = "FindPark";
        //intent.putExtra("scene",msg);
        intent.putExtra("start",1);

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            progress_percent += 15;
                            Thread.sleep(1000);
                            ProgressBar progress = findViewById(R.id.main_progressBar);
                            progress.setProgress(progress_percent);

                            if (progress_percent >= 100) {
                                startActivity(intent);
                                finish();
                                currentThread().interrupt();
                            }
                        }
                    } catch (Throwable t) {

                    } finally {

                    }
                }
            }
        }.start();
    }
}
