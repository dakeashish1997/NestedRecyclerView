package com.ashishdake.nestedrecyclerview.Models;

import com.google.gson.annotations.SerializedName;

public class NestedModel {

    @SerializedName("studentname")
    private String studentname;
    @SerializedName("studentphoto")
    private String studentphoto;

    public NestedModel(String studentname, String studentphoto) {
        this.studentname = studentname;
        this.studentphoto = studentphoto;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentphoto() {
        return studentphoto;
    }

    public void setStudentphoto(String studentphoto) {
        this.studentphoto = studentphoto;
    }
}
