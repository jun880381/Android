package com.example.myapplicationoperationgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class GameviewActivity extends AppCompatActivity {
    private static final String TAG_MSG2 = "operationType";
    TextView tv_value1, tv_value2, tv_operation, tv_score, tv_health;
    EditText et_result;
    Button btn_submit, btn_quit;
    int correctAnswer, value1, value2;
    int score = 0;
    int health = 3;
    Score scoreManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gameview);

        tv_value1 = findViewById(R.id.tv_value1);
        tv_value2 = findViewById(R.id.tv_value2);
        tv_operation = findViewById(R.id.tv_operation);
        tv_score = findViewById(R.id.tv_score);
        tv_health = findViewById(R.id.tv_health);
        et_result = findViewById(R.id.et_result);
        btn_submit = findViewById(R.id.btn_submit);
        btn_quit = findViewById(R.id.btn_quit);

        scoreManager = new Score(this);

        tv_health.setText("남은 기회 : " + health);

        Intent intent = getIntent();
        String operationTypeString = intent.getStringExtra(MainActivity.TAG_MSG);
        OperationType operationType = OperationType.valueOf(operationTypeString);
        Setting(operationType);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (et_result.getText().toString().isEmpty()){
                        Toast.makeText(GameviewActivity.this, "값을 입력해 주세요", Toast.LENGTH_SHORT).show();
                    }

                String resultString = et_result.getText().toString();
                int resultInt = Integer.parseInt(resultString);
                if(resultInt == correctAnswer){
                    Toast.makeText(GameviewActivity.this, "정답입니다.", Toast.LENGTH_SHORT).show();
                    et_result.setText("");
                    score++;
                    tv_score.setText(String.valueOf("점수 : " +score));
                    Setting(operationType);
                }else {
                    Toast.makeText(GameviewActivity.this, "오답입니다.", Toast.LENGTH_SHORT).show();
                    health--;
                    tv_health.setText("남은 기회 : " + health);
                    if(health == 0){
                        showSaveScoreDialog();
                    }
                }
            }catch(NumberFormatException e){
                    Toast.makeText(GameviewActivity.this, "값을 입력해 주세요", Toast.LENGTH_SHORT).show();
                    Log.i("test" , e.getMessage());
                }
            }
        });

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSaveScoreDialog();
            }
        });


    }
    public void Setting(OperationType operationType){
        try{
            Random random = new Random();
            value1 = random.nextInt(999)+1;
            value2 = random.nextInt(999)+1;
            tv_value1.setText(String.valueOf(value1));
            tv_value2.setText(String.valueOf(value2));

            switch(operationType) {
                case ADDITION:
                    tv_operation.setText("+");
                    correctAnswer = value1 + value2;
                    break;
                case SUBSTRACT:
                    if(value1 < value2){
                        int temp = value1;
                        value1 = value2;
                        value2 = temp;
                        tv_value1.setText(String.valueOf(value1));
                        tv_value2.setText(String.valueOf(value2));
                    }
                    tv_operation.setText("-");
                    correctAnswer = value1 - value2;
                    break;
                case MULTIPLE:
                    value1 = random.nextInt(99) + 1;
                    value2 = random.nextInt(9) + 1;
                    tv_value1.setText(String.valueOf(value1));
                    tv_value2.setText(String.valueOf(value2));
                    tv_operation.setText("X");
                    correctAnswer = value1 * value2;
                    break;
                case DIVIVE:
                    value2 = random.nextInt(9) + 1;
                    tv_value2.setText(String.valueOf(value2));
                    tv_operation.setText("÷");
                    correctAnswer = value1 / value2;
                    break;
            }
        }catch(NumberFormatException e){
            Toast.makeText(GameviewActivity.this, "값을 입력해 주세요",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Log.i("test", e.getMessage());
        }

    }
    public void FinishGame(){
        scoreManager.saveScore(score);
        finish();
    }

    private void showSaveScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("게임 종료");
        builder.setMessage("게임이 끝났습니다. 점수를 저장하시겠습니까?");

        builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(GameviewActivity.this,"저장되었습니다.",Toast.LENGTH_SHORT).show();
                FinishGame();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(GameviewActivity.this, "게임이 종료되었습니다.",Toast.LENGTH_SHORT).show();
                FinishGame();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}