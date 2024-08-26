package com.example.myapplicationactmsg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    public static final String TAG_MSG2 = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);

        Button button = findViewById(R.id.button2);
        EditText editText = findViewById(R.id.editText2);
        TextView textView = findViewById(R.id.textView2);

        Intent intent = getIntent();  // 전달된 데이터 수신
        String msg = intent.getStringExtra(MainActivity.TAG_MSG);    // 데이터 추출
        textView.setText(msg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                String msg = editText.getText().toString();
                editText.setText("");
                intent.putExtra(TAG_MSG2,msg);
                startActivity(intent);
            }
        });
    }
}