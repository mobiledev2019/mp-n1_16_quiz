package com.doanhld.quiz.model;

import java.util.ArrayList;

public class Question {
    private int id;
    private String content;
    private ArrayList<Option> optionsInQuestion;


    public Question() {
    }

    public Question(int id, String content){
        this.id = id;
        this.content = content;

    }

    public ArrayList<Option> getOptionsInQuestion() {
        return optionsInQuestion;
    }

    public void setOptionsInQuestion(ArrayList<Option> optionsInQuestion) {
        this.optionsInQuestion = optionsInQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", optionsInQuestion=" + optionsInQuestion +
                '}';
    }
}
