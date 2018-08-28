package com.smtown.yongsangu_ar_project.sookmyung.ending.reward;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;

public class RewardInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_input);


        TextView btn_input = findViewById(R.id.btn_smreward_input);

        btn_input.setOnClickListener(view -> {
            String input_name = ((EditText)findViewById(R.id.et_smreward_name)).getText().toString();
            String input_depart =((EditText)findViewById(R.id.et_smreward_depart)).getText().toString();
            Intent intent = new Intent(RewardInputActivity.this,RewardActivity.class);
            intent.putExtra("input_name",input_name);
            intent.putExtra("input_depart",input_depart);
            startActivity(intent);
        });




    }
}
