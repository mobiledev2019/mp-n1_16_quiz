package com.doanhld.quiz.activity;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.model.Option;
import com.doanhld.quiz.model.Question;
import com.doanhld.quiz.model.Result;
import com.doanhld.quiz.sqlite.Databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotifActivity extends AppCompatActivity {
        Databases databases;
    TextView tvPage;
    TextView tvQuestion;
    RadioGroup radioGroups;
    ArrayList<Option> options;
    ArrayList<Result> correctA;
    List<Question> listData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);


        random();

    }

    private void random() {
        tvQuestion = findViewById(R.id.txtQuestionXXX);

        radioGroups = findViewById(R.id.groupChoiceXXX);


        int max=9, min =3;
//        databases.getCate();
        Random r = new Random();
        int temp = r.nextInt((max - min) + 1) + min;


//        listData = databases.getQuestion(temp);
//        Log.i("AAAAAAAA", temp+"");
//        if (listData.size() == 0) return;
//        temp = r.nextInt((max - min) + 1) + min;
//        Question q = listData.get(temp );
////        tvPage.setText((temp  + 1) + "/" + listData.size());
//        int qid = q.getId();
//        tvQuestion.setText(q.getContent());
//        options = databases.getOption(qid);
//
//        for (int i = 0; i < options.size(); i++) {
//            Option o = options.get(i);
//            RadioButton rd = new RadioButton(NotifActivity.this);
//            rd.setId(o.getId());
//            rd.setText(o.getContent());
//            radioGroups.addView(rd);
//        }
//        tvPage.setText((temp + 1) + "/" + listData.size());
    }
    private void setUpDb() {
        try {

            databases.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }
        try {

            databases.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
    }
}
