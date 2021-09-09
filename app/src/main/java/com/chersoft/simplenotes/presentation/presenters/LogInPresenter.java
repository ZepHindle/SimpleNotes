package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.Service.PasswordValidationResponse;
import com.chersoft.simplenotes.domain.interactors.LogInInteractor;
import com.chersoft.simplenotes.presentation.LogInActivityView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter {

    private final LogInInteractor interactor;
    private LogInActivityView view;

    @Inject
    public LogInPresenter(LogInInteractor interactor){
        this.interactor = interactor;
    }

    public void onCreate(LogInActivityView view){
        this.view = view;
    }

    public void onLogInButton(String userName, String password){
        interactor.logIn(userName, password).enqueue(new Callback<PasswordValidationResponse>() {
            @Override
            public void onResponse(Call<PasswordValidationResponse> call, Response<PasswordValidationResponse> response) {
                PasswordValidationResponse pvr = response.body();
                switch (pvr.getResult()){
                    case PasswordValidationResponse.NO_SUCH_USER:{
                        view.toast(R.string.there_is_no_such_user);
                    }break;
                    case PasswordValidationResponse.WRONG_PASSWORD:{
                        view.toast(R.string.wrong_password);
                    }break;
                    case PasswordValidationResponse.OK:{
                        view.toast(R.string.login_successful);
                        interactor.setUserAccount(userName, password);
                    }break;
                }
            }

            @Override
            public void onFailure(Call<PasswordValidationResponse> call, Throwable t) {
                view.toast(R.string.server_error);
            }
        });
    }
}
