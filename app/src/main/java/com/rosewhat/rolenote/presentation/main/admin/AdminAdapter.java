package com.rosewhat.rolenote.presentation.main.admin;

import android.content.Context;
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

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private ArrayList<User> users;
    private Context context;
    private OnNoteClickListener onNoteClickListener;


    public AdminAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    interface OnNoteClickListener {
        void onNoteClick(int position);

        void onLongClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminAdapter.AdminViewHolder holder, int position) {
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

    class AdminViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitleUser;
        private TextView textViewCommentUser;
        private TextView textViewTribunaUser;
        private TextView textViewPlaceUser;


        public AdminViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewTitleUser = itemView.findViewById(R.id.textViewTitleUser);
            textViewCommentUser = itemView.findViewById(R.id.textViewCommentUser);
            textViewTribunaUser = itemView.findViewById(R.id.textViewTribunaUser);
            textViewPlaceUser = itemView.findViewById(R.id.textViewPlaceUser);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });


        }
    }
}
