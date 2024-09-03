package com.example.myapplicationoperationgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private Context context;
    private List<ScoreItem> scoreList;

    public ScoreAdapter(Context context, List<ScoreItem> scoreList) {
        this.context = context;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_score, parent, false);
        }

        ScoreItem scoreItem = scoreList.get(position);

        TextView scoreTextView = convertView.findViewById(R.id.tv_score_item);
        TextView dateTextView = convertView.findViewById(R.id.tv_date_item);

        scoreTextView.setText(scoreItem.getScore() + "점");
        dateTextView.setText(scoreItem.getDate());

        return convertView;
    }
}
