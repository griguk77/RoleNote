package com.rosewhat.rolenote.presentation.main.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rosewhat.rolenote.R;
import com.rosewhat.rolenote.domain.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> users;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.UserViewHolder holder, int position) {

        User user = users.get(position);
        holder.textViewTitleUser.setText(user.getTitle());
        holder.textViewCommentUser.setText(user.getComment());
        holder.textViewTribunaUser.setText(user.getTribuna());
        holder.textViewPlaceUser.setText(user.getPlace());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitleUser;
        private TextView textViewCommentUser;
        private TextView textViewTribunaUser;
        private TextView textViewPlaceUser;
        public UserViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            textViewTitleUser = itemView.findViewById(R.id.textViewTitleUser);
            textViewCommentUser = itemView.findViewById(R.id.textViewCommentUser);
            textViewTribunaUser = itemView.findViewById(R.id.textViewTribunaUser);
            textViewPlaceUser = itemView.findViewById(R.id.textViewPlaceUser);
        }
    }
}
