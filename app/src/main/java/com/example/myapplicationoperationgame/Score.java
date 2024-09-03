package com.example.myapplicationoperationgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Score {

    public static final String SCORE_LIST_KEY = "score_list";
    private SharedPreferences sharedPreferences;
    private SimpleDateFormat dateFormat;

    public Score(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        dateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분", Locale.getDefault());
    }

    // Method to save score with date and time
    public void saveScore(int score) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String currentDate = dateFormat.format(new Date());
        List<ScoreItem> scoreList = getScoreList();
        if (scoreList == null) {
            scoreList = new ArrayList<>();
        }
        scoreList.add(new ScoreItem(score, currentDate));
        Gson gson = new Gson();
        String json = gson.toJson(scoreList);
        editor.putString(SCORE_LIST_KEY, json);
        editor.apply();
    }

    // Method to get saved score list
    public List<ScoreItem> getScoreList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(SCORE_LIST_KEY, null);
        Type type = new TypeToken<List<ScoreItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void clearScores() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(SCORE_LIST_KEY);
        editor.apply();
    }
}
