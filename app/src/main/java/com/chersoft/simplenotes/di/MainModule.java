package com.chersoft.simplenotes.di;

import android.content.Context;

import com.chersoft.simplenotes.data.Service.Service;
import com.chersoft.simplenotes.data.Service.ServiceAPI;
import com.chersoft.simplenotes.data.repositories.NoteInfoRepositoryImpl;
import com.chersoft.simplenotes.data.repositories.NoteRepositoryImpl;
import com.chersoft.simplenotes.domain.interactors.CreateAccountInteractor;
import com.chersoft.simplenotes.domain.interactors.LogInInteractor;
import com.chersoft.simplenotes.domain.models.UserAccount;
import com.chersoft.simplenotes.domain.repositories.NoteInfoRepository;
import com.chersoft.simplenotes.domain.interactors.NoteInteractor;
import com.chersoft.simplenotes.domain.repositories.NoteRepository;
import com.chersoft.simplenotes.domain.interactors.NotesListInteractor;
import com.chersoft.simplenotes.presentation.presenters.CreateAccountPresenter;
import com.chersoft.simplenotes.presentation.presenters.LogInPresenter;
import com.chersoft.simplenotes.presentation.presenters.NotePresenter;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class MainModule {

    private final Context applicationContext;

    public MainModule(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    @Singleton
    @Provides
    NoteInfoRepository provideNoteInfoRepository(){
        return new NoteInfoRepositoryImpl(applicationContext);
    }

    @Singleton
    @Provides
    NoteRepository provideNoteRepository(){
        return new NoteRepositoryImpl(applicationContext);
    }

    @Singleton
    @Provides
    NotesListViewModel provideNotesListViewModel(){
        return new NotesListViewModel();
    }

    @Provides
    NotesListInteractor provideNotesListInteractor(NoteInfoRepository noteInfoRepository, NoteRepository noteRepository){
        return new NotesListInteractor(noteInfoRepository, noteRepository);
    }

    @Provides
    NotesListPresenter provideNotesListPresenter(NotesListInteractor interactor, NotesListViewModel viewModel){
        return new NotesListPresenter(interactor, viewModel);
    }

    @Provides
    NoteInteractor provideNoteInteractor(NoteRepository noteRepository){
        return new NoteInteractor(noteRepository, applicationContext);
    }

    @Provides
    NotePresenter provideNotePresenter(NoteInteractor noteInteractor){
        return new NotePresenter(noteInteractor);
    }



    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    ServiceAPI provideServiceAPI(Retrofit retrofit){
        return retrofit.create(ServiceAPI.class);
    }

    @Singleton
    @Provides
    UserAccount provideUserAccount(){
        return new UserAccount();
    }

    @Singleton
    @Provides
    Service provideService(Retrofit retrofit, ServiceAPI serviceAPI){
        return new Service(retrofit, serviceAPI);
    }

    @Provides
    CreateAccountInteractor provideAccountInteractor(UserAccount userAccount, Service service){
        return new CreateAccountInteractor(userAccount, service);
    }

    @Provides
    CreateAccountPresenter provideCreateAccountPresenter(CreateAccountInteractor interactor){
        return new CreateAccountPresenter(interactor);
    }

    @Provides
    LogInInteractor provideLogInInteractor(UserAccount userAccount, Service service){
        return new LogInInteractor(userAccount, service);
    }

    @Provides
    LogInPresenter provideLogInPresenter(LogInInteractor inInteractor){
        return new LogInPresenter(inInteractor);
    }

    @Provides
    Context provideContext(){
        return applicationContext;
    }
}
