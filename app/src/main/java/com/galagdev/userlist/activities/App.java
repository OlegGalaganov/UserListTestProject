package com.galagdev.userlist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.galagdev.userlist.R;
import com.galagdev.userlist.di.component.DaggerAppComponent;
import com.galagdev.userlist.di.component.AppComponent;
import com.galagdev.userlist.di.module.AppContextModule;
import com.galagdev.userlist.fragments.AppInfoFragment;
import com.galagdev.userlist.fragments.UserInfoFragment;
import com.galagdev.userlist.fragments.UserListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

public class App extends AppCompatActivity {

    @Inject UserListFragment userListFragment;
    @Inject UserInfoFragment userInfoFragment;
    @Inject AppInfoFragment appInfoFragment;

    private int userId;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inject();
        loadFragment(userListFragment);
        BottomNavigationView navigationView = findViewById(R.id.navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    private void inject() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
        appComponent.inject(this);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fr_content, fragment);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.user_list:
                loadFragment(userListFragment);
                return true;
            case R.id.app_info:
                loadFragment(appInfoFragment);
                return true;
        }
        return false;
    };

}