package com.smtown.yongsangu_ar_project.hyochang.ending.reward;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.sookmyung.ending.reward.RewardActivity;
import com.smtown.yongsangu_ar_project.sookmyung.ending.reward.RewardInputActivity;

public class H_RewardInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h__reward_input);

        TextView btn_input = findViewById(R.id.h_btn_smreward_input);

        btn_input.setOnClickListener(view -> {
            String h_input_name = ((EditText)findViewById(R.id.h_et_smreward_name)).getText().toString();
            String h_input_depart =((EditText)findViewById(R.id.h_et_smreward_depart)).getText().toString();
            Intent intent = new Intent(H_RewardInputActivity.this,H_RewardActivity.class);
            intent.putExtra("h_input_name",h_input_name);
            intent.putExtra("h_input_depart",h_input_depart);
            startActivity(intent);
        });




    }
}
