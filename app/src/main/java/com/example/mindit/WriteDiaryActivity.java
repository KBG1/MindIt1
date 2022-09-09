package com.example.mindit;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Date;
@RequiresApi(api = Build.VERSION_CODES.N)

public class WriteDiaryActivity extends Activity {
    private static final String TAG = "WriteDiaryActivity";

    private DiaryDataBase Diarydb;

    String dateData = new String();
    Date date = new Date();
    String diary_content;
    EditText Edit_diary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writediary);

        TextView DateText = findViewById(R.id.diaryDate);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy년 MM월 dd일의 일기");

        // 날짜 입력하기
        dateData = simpleDate.format(date);
        DateText.setText(String.valueOf(dateData));
        Edit_diary = (EditText) findViewById(R.id.edit_diary);
        diary_content = Edit_diary.getText().toString();

        if (Diarydb != null) {
            Diarydb.close();
            Diarydb = null;
        }

        Diarydb = DiaryDataBase.getInstance(this);
        boolean isOpen = Diarydb.open();
        if (isOpen) {
            Log.d(TAG, "Book database is open.");
        } else {
            Log.d(TAG, "Book database is not open.");
        }


        // 내용 버튼 누르면 저장됌
        Button btn = (Button) findViewById(R.id.btn_diary_store);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 일기를 데이터 베이스에 저장
                Diarydb.insertRecord(dateData, diary_content);
                Toast.makeText(getApplicationContext(), "일기를 추가했습니다.", Toast.LENGTH_LONG).show();
                Diarydb.close();

                // 일기 쓰는 창 종료
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
                finish();

            }
        });

    }
}