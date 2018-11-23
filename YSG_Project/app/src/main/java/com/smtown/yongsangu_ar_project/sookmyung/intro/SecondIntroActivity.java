package com.smtown.yongsangu_ar_project.sookmyung.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smtown.yongsangu_ar_project.R;

public class SecondIntroActivity extends AppCompatActivity {
    ImageView next2;
    ImageView before;
    ImageView chat;
    int next_flag = 0;
    int before_flag= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_intro);
        next2 = findViewById(R.id.btn_next_secondintro);
        before = findViewById(R.id.btn_before_secondintro);
        chat = findViewById(R.id.queen_chat);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SecondIntroActivity.this,ThirdIntroActivity.class);
                //startActivity(intent);

                switch (next_flag){
                    case 0:
                        chat.setImageResource(R.drawable.chat_3);
                        next_flag++;
                        break;
                    case 1:
                        chat.setImageResource(R.drawable.chat_4);
                        next_flag++;
                        break;
                    case 2:
                        chat.setImageResource(R.drawable.chat_5);
                        next_flag++;
                        break;
                    case 3:
                        chat.setImageResource(R.drawable.chat_6);
                        next_flag++;
                        break;
                    case 4:
                        Intent notice = new Intent(SecondIntroActivity.this,ThirdIntroActivity.class);
                        startActivity(notice);

                }


            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (next_flag){
                    case 0:
                        Intent intent = new Intent(SecondIntroActivity.this,FirstIntroActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        chat.setImageResource(R.drawable.chat_2);
                        next_flag--;
                        break;
                    case 2:
                        chat.setImageResource(R.drawable.chat_3);
                        next_flag--;
                        break;
                    case 3:
                        chat.setImageResource(R.drawable.chat_4);
                        next_flag--;
                        break;
                    case 4:
                        chat.setImageResource(R.drawable.chat_5);
                        next_flag--;
                        break;

                }

            }
        });
    }
}
