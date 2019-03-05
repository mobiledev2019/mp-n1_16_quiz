package com.doanhld.quiz.model;

public class Option {
    private int id;
    private int idQuestion;
    private String content;
    private boolean correct;

    public Option(int id, int idQuestion, String content, boolean correct) {

        this.id = id;
        this.idQuestion = idQuestion;
        this.content = content;
        this.correct = correct;
    }

    public Option() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
