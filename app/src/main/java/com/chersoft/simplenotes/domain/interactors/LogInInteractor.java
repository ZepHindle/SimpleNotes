package com.chersoft.simplenotes.domain.interactors;

import com.chersoft.simplenotes.data.Service.PasswordValidationResponse;
import com.chersoft.simplenotes.data.Service.Service;
import com.chersoft.simplenotes.domain.models.UserAccount;

import javax.inject.Inject;

import retrofit2.Call;

public class LogInInteractor {
    private final UserAccount userAccount;
    private final Service service;

    @Inject
    public LogInInteractor(UserAccount userAccount, Service service){
        this.userAccount = userAccount;
        this.service = service;
    }

    public Call<PasswordValidationResponse> logIn(String userName, String password){
        return service.getServiceAPI().logIn(userName, password);
    }

    public void setUserAccount(String userName, String password){
        userAccount.setUserName(userName);
        userAccount.setPassword(password);
    }
}
