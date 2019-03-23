package com.doanhld.quiz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.model.Level;

import java.util.ArrayList;

public class LevelAdapter extends ArrayAdapter<Level> {
    private Context context;
    private int resource;
    private ArrayList<Level> levels;

    public LevelAdapter(@NonNull Context context, int resource, ArrayList<Level> levels) {
        super(context, resource,levels);
        this.context = context;
        this.resource = resource;
        this.levels = levels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_topic = convertView.findViewById(R.id.tv_topic);
            viewHolder.tvSTT = convertView.findViewById(R.id.tvSTT);
            viewHolder.ratingBar = convertView.findViewById(R.id.rating);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        Level levelModel = levels.get(position);
        viewHolder.tv_topic.setText(levelModel.getTitleTopic());
        viewHolder.tvSTT.setText(String.valueOf(position+1));
        viewHolder.ratingBar.setStepSize(levelModel.getSl () == 0 ? 0 : (float)(5/levelModel.getSl()));
        viewHolder.ratingBar.setRating(((float) levelModel.getScore()/levelModel.getSl())*5);

        return convertView;
    }
    public class ViewHolder{
        TextView tv_topic;
        TextView tvSTT;
        RatingBar ratingBar;
    }
}
