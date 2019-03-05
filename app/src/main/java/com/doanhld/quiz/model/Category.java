package com.doanhld.quiz.model;

public class Category {
    private String titleCatelogy;
    private String id;
    public Category() {
    }

    public Category(String titleCatelogy, String id) {
        this.titleCatelogy = titleCatelogy;
        this.id = id;
    }

    public String getTitleCatelogy() {
        return titleCatelogy;
    }

    public void setTitleCatelogy(String titleCatelogy) {
        this.titleCatelogy = titleCatelogy;
    }

    @Override
    public String toString() {
        return titleCatelogy ;
    }
}
