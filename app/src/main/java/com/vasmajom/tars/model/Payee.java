package com.vasmajom.tars.model;

import java.util.List;

public class Payee {

    private String name;
    private List<Wallet> walletList;

    public Payee(String name, List<Wallet> walletList) {
        this.name = name;
        this.walletList = walletList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }

    public void addWallet(Wallet wallet) {
        walletList.add(wallet);
    }
}
