package com.galagdev.userlist.di.component;

import com.galagdev.userlist.di.module.ApiServiceModule;
import com.galagdev.userlist.di.module.RoomModule;
import com.galagdev.userlist.di.module.ViewModelModule;
import com.galagdev.userlist.di.scope.ViewModelScope;
import com.galagdev.userlist.viewmodel.UserViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = {ApiServiceModule.class, RoomModule.class, ViewModelModule.class})
@ViewModelScope
public interface ViewModelComponent {

    void inject(UserViewModel viewModel);
}
