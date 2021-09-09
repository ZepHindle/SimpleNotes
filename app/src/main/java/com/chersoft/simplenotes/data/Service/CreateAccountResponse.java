package com.chersoft.simplenotes.data.Service;

public class CreateAccountResponse {

    public static int USER_CREATED = 1;
    public static int USER_ALREADY_EXISTS = 2;

    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
