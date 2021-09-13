package com.chersoft.simplenotes.domain.interactors;

import com.chersoft.simplenotes.data.Service.CreateAccountResponse;
import com.chersoft.simplenotes.data.Service.Service;
import com.chersoft.simplenotes.domain.models.UserAccount;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountInteractor {

    private final UserAccount userAccount;
    private final Service service;

    @Inject
    public CreateAccountInteractor(UserAccount userAccount, Service service){
        this.userAccount = userAccount;
        this.service = service;
    }

    /**
     * СОздает объект запроса к серверу на создание аккаунта.
     * @param userName имя пользователя
     * @param password пароль пользователя
     * @return объект для управления запросом
     */
    public Call<CreateAccountResponse> createUser(String userName, String password){
        return service.getServiceAPI().createAccount(userName, password);
    }

    /**
     * Записывает данные текущего аккаунта.
     * @param userName имя пользователя
     * @param password пароль пользователя
     */
    public void setUserAccount(String userName, String password){
        userAccount.setUserName(userName);
        userAccount.setPassword(password);
    }

}
