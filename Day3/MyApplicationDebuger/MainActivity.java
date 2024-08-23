package com.example.myapplicationdebuger;

import android.os.Bundle;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        int result = 0;
        int a = 2;
        int b = 3;
        int c = 5;
        result = a << 2;        // 결과값 : 8 (a를 좌측으로 2비트 만큼 움직이다)
        result += b;            // 결과값 : 11
        result = (result + c) >> 1;     // 결과값 : 8 (11 + 5)를 우측으로 1비트 움직임
        result = add(result, 3);     // 결과값 : 11 (8 + 3)
        textView.setText(String.valueOf(result));
    }
    int add(int a, int b){
        int sum = 0;
        sum = a;        // sum = a = result = 8
        sum += b;       // aum += b = sum + 3 = result + 3 = 8 + 3                  += : 복합 대입 연산자
        return sum;
    }
}