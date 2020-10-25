package com.galagdev.userlist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galagdev.userlist.R;
import com.galagdev.userlist.model.pogo.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> users;
    private static OnUserClickListener onUserClickListener;

    public interface OnUserClickListener {
        void onUserClick(int position);
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        UserAdapter.onUserClickListener = onUserClickListener;
    }

    public UserAdapter() {
        users = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewFirstName.setText(user.getFirstName());
        holder.textViewLastName.setText(user.getLastName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFirstName;
        private TextView textViewLastName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFirstName = itemView.findViewById(R.id.textViewFirstName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            itemView.setOnClickListener(v -> {
                if (onUserClickListener != null) {
                    onUserClickListener.onUserClick(getAdapterPosition());
                }
            });
        }
    }
}
