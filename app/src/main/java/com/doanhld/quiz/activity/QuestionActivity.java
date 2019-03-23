package com.doanhld.quiz.activity;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.dialog.EnglishDialog;
import com.doanhld.quiz.model.Option;
import com.doanhld.quiz.model.Question;
import com.doanhld.quiz.model.Result;
import com.doanhld.quiz.sqlite.Databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements EnglishDialog.EnglishDialogListener{

    final public static String KEY_TITLE = "TitleQuestionActivity";
    Button btnNext;
    Button btnPre;
    TextView tvPage;
    TextView tvQuestion;
    RadioGroup radioGroups;
    ArrayList<Option> options;
    ArrayList<Result> correctA;
    int id;
    int level_score_id;
    Databases databases;
    HashMap<Integer, String> map = new HashMap<>();

    int index = 0;
    int score = 0;
    EnglishDialog englishDialog;
    List<Question> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getIntent().getStringExtra(KEY_TITLE));
        correctA = new ArrayList<>();
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPrevious);
        tvPage = findViewById(R.id.txtPage);
        tvQuestion = findViewById(R.id.txtQuestion);
        btnPre.setEnabled(false);
        radioGroups = findViewById(R.id.groupChoice);
        radioGroups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rd = findViewById(radioGroup.getCheckedRadioButtonId());
                if (rd != null) {
                    map.put(index, rd.getText().toString());
                }
            }
        });

        databases = new Databases(this);
        id = getIntent().getIntExtra("ID", 0);
        level_score_id = getIntent().getIntExtra("level_score_id", 0);
        setUpDb();
        prepareData(id);
        addEvent();
        correctA = databases.getResult(id);

        englishDialog = new EnglishDialog();
        englishDialog.setCancelable(false);
        englishDialog.setEnglishDialogListener(this);
    }


    private void addEvent() {
        //xu ly su kien cho nut Pre
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroups.removeAllViews();
                index--;
                tvPage.setText((index + 1) + "/" + listData.size());
                Question q = listData.get(index);
                tvQuestion.setText(q.getContent());
                int qid = q.getId();
                ArrayList<Option> optionModels = databases.getOption(qid);
                for (int i = 0; i < optionModels.size(); i++) {
                    Option o = optionModels.get(i);
                    RadioButton rd = new RadioButton(QuestionActivity.this);
                    rd.setId(o.getId());
                    rd.setText(o.getContent());
                    if (o.getContent().equals(map.get(index))) rd.setChecked(true);
                    radioGroups.addView(rd);
                }
                if (index == 0) {
                    btnPre.setEnabled(false);
                }
            }
        });
        // Xu ly su kien cho nut Next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == listData.size() - 1) {
                    englishDialog.show(getSupportFragmentManager(), "TEST");

                } else {
                    radioGroups.removeAllViews();
                    index++;
                    tvPage.setText((index + 1) + "/" + listData.size());
                    Question q = listData.get(index);
                    tvQuestion.setText(q.getContent());
                    int qid = q.getId();
                    ArrayList<Option> optionModels = databases.getOption(qid);
                    for (int i = 0; i < optionModels.size(); i++) {
                        Option o = optionModels.get(i);
                        RadioButton rd = new RadioButton(QuestionActivity.this);
                        rd.setId(o.getId());
                        rd.setText(o.getContent());
                        if (o.getContent().equals(map.get(index))) rd.setChecked(true);
                        radioGroups.addView(rd);
                    }
                }
                if (index != 0) {
                    btnPre.setEnabled(true);
                }
            }

        });
    }

    private void prepareData(int id) {

        listData = databases.getQuestion(id);
        Log.i("qID", id+"");
        if (listData.size() == 0) return;
        Question q = listData.get(index);
        tvPage.setText((index + 1) + "/" + listData.size());
        int qid = q.getId();
        tvQuestion.setText(q.getContent());
        options = databases.getOption(qid);

        for (int i = 0; i < options.size(); i++) {
            Option o = options.get(i);
            RadioButton rd = new RadioButton(QuestionActivity.this);
            rd.setId(o.getId());
            rd.setText(o.getContent());
            radioGroups.addView(rd);
        }
        tvPage.setText((index + 1) + "/" + listData.size());
    }


    @Override
    public void onPosClickListener() {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra("ID", id);
        intent.putExtra("KQ", map);
        intent.putExtra("lsd", level_score_id);
        intent.putExtra("score", getIntent().getIntExtra("score", 0));
        Intent kqIntent = new Intent();
        for (int i = 0; i < correctA.size(); i++) {
            if (correctA.get(i).getCorrectAnswer().equals(map.get(i))) score++;
        }
        kqIntent.putExtra("score", score);
        setResult(RESULT_OK, kqIntent);

        finish();
        startActivity(intent);
    }


    @Override
    public void onNeClickListener() {
        englishDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
