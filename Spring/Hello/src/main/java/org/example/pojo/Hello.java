package org.example.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

//会被spring容器托管
@Component
public class Hello {
    @Value("Jack")
    private String name;
    private String[] books;
    private int age;

    public Hello() {
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
        return name;
    }
}
