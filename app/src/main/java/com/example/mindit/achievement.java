package com.example.mindit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class achievement extends AppCompatActivity {

    ImageView image[] = new ImageView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todayachievement);
        setTitle("성취도");

        final int achieveCount[] = new int[5];
        for(int i = 0; i < 5; i++) {
            achieveCount[i] = 0;
        }

        Integer imageID[] = { R.id.ah0, R.id.ah25, R.id.ah50, R.id.ah75, R.id.ah100};

        final String imageName[] = { "성취도0", "성취도25","성취도50", "성취도75", "성취도100"};

        for(int i = 0; i < imageID.length; i++) {
            final int index;
            index = i;
            image[index] = (ImageView) findViewById(imageID[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
// 클릭시 아이콘 크기 바뀌는 거
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                    view.startAnimation(anim);
// 이미지 클릭시 이미지의 클릭 횟수가 증가함.
                    achieveCount[index]++;
                    Toast.makeText(getApplicationContext(), imageName[index] + ": 총 " + achieveCount[index] + "표 ",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnFinish = (Button) findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// 버튼을 누르면, 이미지를 클릭한 횟수를 결과 코드에 전달 해줌.
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("achieveCount", achieveCount);
                intent.putExtra("ImageName", imageName);
                startActivity(intent);

            }
        });
    }
}
