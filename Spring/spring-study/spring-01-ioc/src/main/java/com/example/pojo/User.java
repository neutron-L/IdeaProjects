package com.example.pojo;

public class User {
    public User(String s) {
        System.out.println("User creator executed");
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public User() {
        System.out.println("User default creator executed");
    }

    public void show() {
        System.out.println("name = " + name);
    }

}
