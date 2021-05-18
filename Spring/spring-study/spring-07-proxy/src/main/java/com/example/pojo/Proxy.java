package com.example.pojo;

public class Proxy implements Rent {
    private Landlord landlord;
    public Proxy() {

    }
    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }


    @Override
    public void rent() {
        brokerageFee();
        landlord.rent();
        signCompact();
    }

    public void brokerageFee() {
        System.out.println("get brokerage fee");
    }

    public void signCompact() {
        System.out.println("sign the contract");
    }
}


