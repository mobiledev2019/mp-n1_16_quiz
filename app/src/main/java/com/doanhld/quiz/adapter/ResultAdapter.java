package com.doanhld.quiz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.model.Result;

import java.util.ArrayList;

public class ResultAdapter extends ArrayAdapter<Result> {
    private Context context;
    private int resource;
    private ArrayList<Result> results;
    public ResultAdapter(@NonNull Context context, int resource, ArrayList<Result> results) {
        super(context, resource,results);
        this.context = context;
        this.resource = resource;
        this.results = results;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvResult1 = convertView.findViewById(R.id.tv_result1);
            viewHolder.tvResult2 = convertView.findViewById(R.id.tv_result2);
            viewHolder.tvResult3 = convertView.findViewById(R.id.tv_result3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Result resultModel = results.get(position);
        viewHolder.tvResult1.setText(resultModel.getQuestion());
        if(resultModel.getKQ()){
            viewHolder.tvResult2.setText("");
            viewHolder.tvResult3.setText("Correct answer: "+resultModel.getCorrectAnswer());
        } else if (resultModel.getYourAnswer()==null) {
            viewHolder.tvResult2.setText("Your answer: "+" ");
            viewHolder.tvResult3.setText("Correct answer: "+resultModel.getCorrectAnswer()) ;
        } else {
            viewHolder.tvResult2.setText("Your answer: "+resultModel.getYourAnswer());
            viewHolder.tvResult3.setText("Correct answer: "+resultModel.getCorrectAnswer());
        }

        return convertView;
    }
    public class ViewHolder{
        TextView tvResult1,tvResult2,tvResult3;
    }
}
