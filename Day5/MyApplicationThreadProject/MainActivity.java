package com.example.myapplicationthreadproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private int counter = 0;
    private boolean isRunning = true;
    private Semaphore semaphore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        semaphore.acquire(); // key 획득
                        for(int i = 0; i < 10000000; i++){
                            counter++;
                        }
                        semaphore.release(); // key 반환
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("test" , "thread running" + counter);
                    runOnUiThread(new Runnable() {      // UI 업데이트
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(counter));
                        }
                    });
                }
        });
        thread.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    for(int i = 0; i < 10000000; i++){
                        counter--;
                    }
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("test" , "thread running" + counter);
                runOnUiThread(new Runnable() {      // UI 업데이트
                    @Override
                    public void run() {
                        textView.setText(String.valueOf(counter));
                    }
                });
            }
        });
        thread2.start();
    }
    // 액티비티 종료시 쓰레드 종료 (메모리 누수 방지)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}