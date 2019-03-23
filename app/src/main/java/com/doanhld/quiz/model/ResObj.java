package com.doanhld.quiz.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResObj implements Serializable {
    @SerializedName("data")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
