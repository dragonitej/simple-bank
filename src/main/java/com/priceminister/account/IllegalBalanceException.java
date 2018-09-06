package com.priceminister.account;


public class IllegalBalanceException extends Exception {
    
    private static final long serialVersionUID = -9204191749972551939L;
    public IllegalBalanceException(Double illegalBalance) {
        super("Illegal account balance: " + illegalBalance);
    }
}
