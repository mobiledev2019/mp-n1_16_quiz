package com.doanhld.quiz.activity;

import android.content.Intent;
import android.database.SQLException;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.adapter.LevelAdapter;
import com.doanhld.quiz.model.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.doanhld.quiz.adapter.CategoryAdapter;
import com.doanhld.quiz.model.Level;
import com.doanhld.quiz.sqlite.Databases;

public class MainActivity extends AppCompatActivity {

    ListView lvCategory;
    ListView lvTopic;
    ArrayList<Category> categories;
    ArrayList<Level> levels = new ArrayList<>();
    Databases databases;
    int selectedLevel = 1;
    LevelAdapter levelAdapter;
    int selectedCate = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databases = new Databases(this);
        setUpDb();
        addCatelogy();
        addLevel();

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

    private void addCatelogy() {
//        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        final Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        //
        categories = databases.getCate();
        getSupportActionBar().setTitle(categories.get(0).getTitleCatelogy());
        lvCategory = findViewById(R.id.list_item1);
        CategoryAdapter catelogyAdapter = new CategoryAdapter(this, R.layout.item_category, categories);
        lvCategory.setAdapter(catelogyAdapter);
        lvCategory.setOnItemClickListener((adapterView, view, i, l) -> {
//            toolbar.setTitle(categories.get(i).getTitleCatelogy());
            if (i+1 != selectedLevel) {
                selectedCate = i+1;
                Log.i("id", String.valueOf(selectedCate));
                addLevel();
            }
//            drawer.closeDrawers();
        });
    }
    private void addLevel() {

        levels = databases.getTopics(selectedCate);
        lvTopic = findViewById(R.id.list_viewTopic);
        levelAdapter = new LevelAdapter(this, R.layout.item_topic,levels);
        lvTopic.setAdapter(levelAdapter);
        lvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (levels.get(i).getSl() == 0) return;
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra(QuestionActivity.KEY_TITLE, String.valueOf(levels.get(i)));
                intent.putExtra("ID", levels.get(i).getId());
                intent.putExtra("level_score_id", levels.get(i).getLevelscore_id());
                intent.putExtra("score",levels.get(i).getScore());
                selectedLevel = i;
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                int d = data.getIntExtra("score", 0);
                if (d > levels.get(selectedLevel).getScore()) {
                    levels.get(selectedLevel).setScore(d);
                    levelAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
