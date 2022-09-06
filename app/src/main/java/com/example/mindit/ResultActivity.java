package com.example.mindit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "AchieveActivity";
    private AcheiveDataBase Achievedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievementresult);
        setTitle("투표 결과");

        if (Achievedb != null) {
            Achievedb.close();
            Achievedb = null;
        }

        Achievedb = AcheiveDataBase.getInstance(this);

        boolean isOpen = Achievedb.open();
        if (isOpen) {
            Log.d(TAG, "Achieve database is open.");
        } else {
            Log.d(TAG, "Achieve database is not open.");
        }

        Intent intent = getIntent();
        // 이미지들 각각의 투표 횟수가 전달 됌.
        int[] voteResult = intent.getIntArrayExtra("achieveCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");


        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];


        Integer tvID[] = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5};

        Integer rbarID[] = { R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
                R.id.rbar5};


        // voteresult 배열의 크기는 당연히 5임. 그러므로, 5만큼 for문을 돌려서, textview 와 레이팅바의 xml과 java 코드를 연결해줌.
        for (int i = 0; i < voteResult.length; i++) {
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        // for문을 5만큼 반복하고, tv의 각각의 값에, imageName[i] 배열의 값을 연결 하고, 레이팅 바에 수치를 achievement 에서 intent 를 통해 넘겨 받은,
        // 값을 float 형으로 넣어준다. 즉, 데이터 베이스에 저장이 되어야 하는 값은, tvID 와 voteResult[i] 임. tvID 를 받아서, 데베에 저장하고, 이를 tv 객체에 다시 넣어주어야 함.
        for (int i = 0; i < voteResult.length; i++) {
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float) voteResult[i]);
        }
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "성취도 입력 완료", Toast.LENGTH_LONG).show();

                finish();
            }
        });

    }
}