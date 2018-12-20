package com.smtown.bgm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.smtown.yongsangu_ar_project.R;

public class Game_Main_Bgm extends Service {
    public MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void onStart(Intent intent, int startId){
        Log.i("Example", "Service onStart()");

        super.onStart(intent, startId);
        mp = MediaPlayer.create(this, R.raw.game_main_bgm);
        mp.setLooping(true); // 반복 재생 설정 (true와 false로 조정 가능)
        mp.start(); //음악 재생


    }

    public void onDestroy() {
        Log.i("Example", "Service onDestroy()");
        super.onDestroy();
        mp.stop(); //음악 정지
    }


}
