package com.galagdev.userlist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.galagdev.userlist.R;
import com.galagdev.userlist.activities.App;
import com.galagdev.userlist.model.pogo.User;
import com.galagdev.userlist.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class UserInfoFragment extends Fragment{

    private ImageView imageViewUserAvatar;
    private TextView textViewUserFirstName;
    private TextView textViewUserLastName;
    private TextView textViewUserEmail;

    private UserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        initView(view);
        observeData();
        return view;
    }

    private void observeData() {
        viewModel.getUserById().observe(this, this::createUserInfo);
        viewModel.getErrors().observe(this, throwable -> {
            if (throwable != null) {
                Toast.makeText(getContext(), "Ошибка загрузки", Toast.LENGTH_SHORT).show();
                viewModel.clearErrors();
            }
        });
        viewModel.loadUserById(((App) Objects.requireNonNull(getActivity())).getUserId());
    }

    private void initView(View view) {
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(Objects.requireNonNull(getActivity()).getApplication())).get(UserViewModel.class);
        imageViewUserAvatar = view.findViewById(R.id.imageViewUserAvatar);
        textViewUserFirstName = view.findViewById(R.id.textViewDetailFirstName);
        textViewUserLastName = view.findViewById(R.id.textViewDetailLastName);
        textViewUserEmail = view.findViewById(R.id.textViewUserEmail);
    }

    private void createUserInfo(User user) {
        textViewUserFirstName.setText(user.getFirstName());
        textViewUserLastName.setText(user.getLastName());
        textViewUserEmail.setText(user.getEmail());
        Picasso.get().load(user.getAvatar()).into(imageViewUserAvatar);
    }
}
