package org.example;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class Hello {
    private String name;
    private String[] books;
    private int age;

    public Hello(String name, int age) {

    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public Set<Double> getScores() {
        return scores;
    }

    public void setScores(Set<Double> scores) {
        this.scores = scores;
    }

    private Set<Double> scores;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Name is " + this.name);
        return name;
    }
}
