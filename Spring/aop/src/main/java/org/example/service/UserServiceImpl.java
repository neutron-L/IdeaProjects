package org.example.service;

public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("add method executed");
    }

    @Override
    public void delete() {
        System.out.println("delete method executed");
    }

    @Override
    public void update() {
        System.out.println("update method executed");
    }

    @Override
    public void select() {
        System.out.println("select method executed");
    }
}
