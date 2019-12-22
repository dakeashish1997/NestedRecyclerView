package com.ashishdake.nestedrecyclerview.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainModel {

    @SerializedName("classname")
    private String classname;
    @SerializedName("students")
    private List<NestedModel> list;

    public MainModel(String classname, List<NestedModel> list) {
        this.classname = classname;
        this.list = list;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<NestedModel> getList() {
        return list;
    }

    public void setList(List<NestedModel> list) {
        this.list = list;
    }
}
