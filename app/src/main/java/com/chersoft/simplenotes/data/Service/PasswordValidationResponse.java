package com.chersoft.simplenotes.data.Service;

public class PasswordValidationResponse {

    public static final int NO_SUCH_USER = 0;
    public static final int WRONG_PASSWORD = 1;
    public static final int OK = 2;

    public static final PasswordValidationResponse NO_SUCH_USER_PI = new PasswordValidationResponse(NO_SUCH_USER);
    public static final PasswordValidationResponse WRONG_PASSWORD_PI = new PasswordValidationResponse(WRONG_PASSWORD);
    public static final PasswordValidationResponse OK_PI = new PasswordValidationResponse(OK);

    private int result;

    public PasswordValidationResponse(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
