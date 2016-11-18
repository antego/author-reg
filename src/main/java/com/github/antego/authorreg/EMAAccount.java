package com.github.antego.authorreg;

import java.math.BigInteger;

public class EMAAccount {
    private BigInteger address;
    private BigInteger privateKey;

    public BigInteger getAddress() {
        return address;
    }

    public void setAddress(BigInteger address) {
        this.address = address;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }
}
