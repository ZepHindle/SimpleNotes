package com.chersoft.simplenotes.data.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {
    @GET("createaccount/")
    Call<CreateAccountResponse> createAccount(@Query("userName") String userName,
                                              @Query("password") String password);

    @GET("validate/")
    Call<PasswordValidationResponse> logIn(@Query("userName") String userName,
                                           @Query("password") String password);
}
