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
    private Call<PasswordValidationResponse> call;

    @Inject
    public LogInPresenter(LogInInteractor interactor){
        this.interactor = interactor;
    }

    /**
     * Вызывается при вызове onCreate активности.
     * @param view view в MVP
     */
    public void onCreate(LogInActivityView view){
        this.view = view;
        view.setProgressBarVisible(false);
    }

    /**
     * Вызывается при вызове onStop активности.
     */
    public void onStop(){
        if (call != null) call.cancel();
    }

    /**
     * Вызывается при нажатии на кнопку "log in"
     * @param userName имя пользователя
     * @param password пароль
     */
    public void onLogInButton(String userName, String password){
        view.setProgressBarVisible(true);

        if (call != null) call.cancel();

        this.call = interactor.logIn(userName, password);
        call.enqueue(new Callback<PasswordValidationResponse>() {
            @Override
            public void onResponse(Call<PasswordValidationResponse> call, Response<PasswordValidationResponse> response) {
                view.setProgressBarVisible(false);
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
                LogInPresenter.this.call = null;
            }

            @Override
            public void onFailure(Call<PasswordValidationResponse> call, Throwable t) {
                if (!call.isCanceled()){
                    view.toast(R.string.server_error);
                    view.setProgressBarVisible(false);
                }
                LogInPresenter.this.call = null;
            }
        });
    }
}
