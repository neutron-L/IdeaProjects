package com.example.pojo;

import lombok.Data;

@Data
public class Books {

    private int id;
    private  String name;
    private int counts;
    private String detail;

    public Books() {
    }

    public Books(int id, String name, int counts, String detail) {
        this.id = id;
        this.name = name;
        this.counts = counts;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}

