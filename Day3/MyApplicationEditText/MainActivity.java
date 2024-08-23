package com.example.myapplicationedittext;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText, editText2;
    private Button button, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        editText2 = findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString(); // 에딧텍스트의 입력을 문자열로 가져옴
                editText2.setText(str);
                editText.setText("");

            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String str = editText2.getText().toString();
                editText.setText(str);
                editText2.setText("");
            }
        });

    }
}