package org.example;

public class Hello {
    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Name is " + this.name);
        return name;
    }
}
