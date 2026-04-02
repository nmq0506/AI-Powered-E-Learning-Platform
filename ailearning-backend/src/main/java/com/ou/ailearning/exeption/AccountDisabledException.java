package com.ou.ailearning.exeption;

public class AccountDisabledException extends RuntimeException {
    public AccountDisabledException(String msg) {
        super(msg);
    }
}
