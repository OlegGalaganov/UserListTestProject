package com.galagdev.userlist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galagdev.userlist.R;
import com.galagdev.userlist.activities.App;
import com.galagdev.userlist.adapters.UserAdapter;
import com.galagdev.userlist.di.component.AppComponent;
import com.galagdev.userlist.di.component.DaggerAppComponent;
import com.galagdev.userlist.di.module.AppContextModule;
import com.galagdev.userlist.model.pogo.User;
import com.galagdev.userlist.viewmodel.UserViewModel;

import java.util.Objects;

import javax.inject.Inject;

public class UserListFragment extends Fragment {

    @Inject UserAdapter adapter;
    @Inject UserInfoFragment userInfoFragment;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_user_list, container, false);
        inject();
        initView(fragment);
        observeData();
        listenToTheClick();
        return fragment;
    }

    private void inject() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(getContext()))
                .build();
        appComponent.inject(this);
    }

    private void observeData() {
        setLoading(true);
        viewModel.getUsers().observe(this, users -> {
            setLoading(false);
            adapter.setUsers(users);
        });
        viewModel.getErrors().observe(this, throwable -> {
            if (throwable != null) {
                setLoading(false);
                Toast.makeText(getContext(), "Ошибка загрузки", Toast.LENGTH_SHORT).show();
                viewModel.clearErrors();
            }
        });
        viewModel.loadUsers();
    }

    private void listenToTheClick() {
        adapter.setOnUserClickListener(position -> {
            User user = adapter.getUsers().get(position);
            ((App) Objects.requireNonNull(getActivity())).setUserId(user.getId());
            if (getActivity() != null) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fr_content, userInfoFragment).commit();
            }
        });
    }

    private void initView(View view) {
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(((Objects.requireNonNull(getActivity())).getApplication()))).get(UserViewModel.class);
        recyclerView = view.findViewById(R.id.recycleViewUsers);
        progressBar = view.findViewById(R.id.progressBarLoading);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    public void setLoading(boolean isLoading) {
        if (isLoading && progressBar.getVisibility() == View.INVISIBLE)
            progressBar.setVisibility(View.VISIBLE);
        if (!isLoading && progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.INVISIBLE);
    }
}
