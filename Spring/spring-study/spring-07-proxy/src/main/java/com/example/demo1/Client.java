package com.example.demo1;

public class Client {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setRent(landlord);

        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }
}
