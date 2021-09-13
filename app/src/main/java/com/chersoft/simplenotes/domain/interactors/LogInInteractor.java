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

    /**
     * Создает объект запроса к серверу на проверку имени пользователя и пароля.
     * @param userName имя пользователя
     * @param password пароль
     * @return объект для управления запросом
     */
    public Call<PasswordValidationResponse> logIn(String userName, String password){
        return service.getServiceAPI().logIn(userName, password);
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
