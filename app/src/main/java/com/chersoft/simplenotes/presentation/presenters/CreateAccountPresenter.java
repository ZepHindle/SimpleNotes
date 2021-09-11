package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.Service.CreateAccountResponse;
import com.chersoft.simplenotes.domain.interactors.CreateAccountInteractor;
import com.chersoft.simplenotes.presentation.CreateAccountView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountPresenter {

    private final CreateAccountInteractor interactor;
    private CreateAccountView view;

    @Inject
    public CreateAccountPresenter(CreateAccountInteractor interactor){
        this.interactor = interactor;
    }

    public void onCreate(CreateAccountView view){
        this.view = view;
        view.setProgressBarVisible(false);
    }

    public void onRegisterButtonPress(String userName, String password, String passwordRepeat){
        if (!password.equals(passwordRepeat)) {
            view.toast(R.string.passwords_are_not_the_same);
            return;
        }
        view.setProgressBarVisible(true);
        interactor.createUser(userName, password).enqueue(new Callback<CreateAccountResponse>() {
            @Override
            public void onResponse(Call<CreateAccountResponse> call, Response<CreateAccountResponse> response) {
                CreateAccountResponse createAccountResponse = response.body();
                if (createAccountResponse.getResult() == CreateAccountResponse.USER_CREATED){
                    view.toast(R.string.user_created);
                    interactor.setUserAccount(userName, password);
                } else {
                    view.toast(R.string.user_already_exists);
                }
                view.setProgressBarVisible(false);
            }

            @Override
            public void onFailure(Call<CreateAccountResponse> call, Throwable t) {
                view.toast(R.string.server_error);
                view.setProgressBarVisible(false);
            }
        });
    }
}
