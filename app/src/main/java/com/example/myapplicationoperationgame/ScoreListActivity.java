package com.example.myapplicationoperationgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class ScoreListActivity extends Activity {

    private ListView listView;
    private ScoreAdapter scoreAdapter;
    private List<ScoreItem> scoreItemList = new ArrayList<>();
    private Button backBtn;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        listView = findViewById(R.id.list_view_scores);
        backBtn = findViewById(R.id.backBtn);
        clearBtn = findViewById(R.id.clearBtn);

        Score scoreManager = new Score(this);
        scoreItemList = scoreManager.getScoreList(); // Get the list of scores

        if (scoreItemList == null) {
            scoreItemList = new ArrayList<>();
        }

        scoreAdapter = new ScoreAdapter(this, scoreItemList);
        listView.setAdapter(scoreAdapter);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ScoreListActivity.this);
                builder.setTitle("점수 초기화");
                builder.setMessage("점수를 모두 지우시겠습니까?");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ScoreListActivity.this, "초기화 되었습니다.",Toast.LENGTH_SHORT).show();
                        scoreManager.clearScores();
                        scoreItemList.clear();
                        scoreAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

}
