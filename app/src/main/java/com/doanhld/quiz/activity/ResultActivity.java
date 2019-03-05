package com.doanhld.quiz.activity;

import android.database.SQLException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.adapter.ResultAdapter;
import com.doanhld.quiz.model.Result;
import com.doanhld.quiz.sqlite.Databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {
    ListView lvResult;
    int id;
    TextView tvImage;
    ImageButton imResult;
    ArrayList<Result> resultModels;
    Databases databases;
    HashMap<Integer, String> map;
    int score=0;
    int lsd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        databases = new Databases(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("RESULT TEST");
        tvImage = findViewById(R.id.tv_result);
        setUpDb();
        id = getIntent().getIntExtra("ID",0);
        resultModels = databases.getResult(id);
        map = (HashMap<Integer, String>) getIntent().getSerializableExtra("KQ");
        lsd = getIntent().getIntExtra("lsd", 0);
        lvResult = findViewById(R.id.listResult);

        for (int i = 0 ; i < resultModels.size(); i++) {
            resultModels.get(i).setYourAnswer(map.get(i));
            if (resultModels.get(i).getKQ()) score++;
        }

        ResultAdapter resultAdapter = new ResultAdapter(this,R.layout.item_result,resultModels);
        if (lsd == 0) {
            databases.insertToDB(id,score);
        }
        else {
            int diem = getIntent().getIntExtra("score",0);
            if(score > diem) databases.updateLevelScore(lsd,id,score);
        }
        tvImage.setText("Correct: "+score+"/"+resultModels.size());
        lvResult.setAdapter(resultAdapter);
        imResult = findViewById(R.id.ig_result);
        imResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void setUpDb() {
        try {

            databases.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            databases.openDataBase();

        } catch (SQLException sqle) {

            throw sqle;

        }
    }
}
