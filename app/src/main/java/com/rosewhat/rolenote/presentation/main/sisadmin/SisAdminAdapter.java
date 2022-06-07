package com.rosewhat.rolenote.presentation.main.sisadmin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rosewhat.rolenote.R;
import com.rosewhat.rolenote.domain.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SisAdminAdapter extends RecyclerView.Adapter<SisAdminAdapter.SisAdminViewHolder> {

    private ArrayList<User> users;
    private Context context;

    public SisAdminAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public SisAdminViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sisadmin_item, parent, false);
        return new SisAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SisAdminAdapter.SisAdminViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewTitleSisAdmin.setText(user.getTitle());
        holder.textViewCommentSisAdmin.setText(user.getComment());
        holder.textViewTribunaSisAdmin.setText(user.getTribuna());
        holder.textViewPlaceSisAdmin.setText(user.getPlace());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class SisAdminViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitleSisAdmin;
        private TextView textViewCommentSisAdmin;
        private TextView textViewTribunaSisAdmin;
        private TextView textViewPlaceSisAdmin;
        private Button buttonBuy;
        private Button buttonSend;
        public SisAdminViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewTitleSisAdmin = itemView.findViewById(R.id.textViewTitleSisAdmin);
            textViewCommentSisAdmin = itemView.findViewById(R.id.textViewCommentSisAdmin);
            textViewTribunaSisAdmin = itemView.findViewById(R.id.textViewTribunaSisAdmin);
            textViewPlaceSisAdmin = itemView.findViewById(R.id.textViewPlaceSisAdmin);
            buttonBuy = itemView.findViewById(R.id.buttonBuy);
            buttonSend = itemView.findViewById(R.id.buttonSend);

            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Цска - Спартак");
                    Intent chose = Intent.createChooser(intent, "Выберите приложение");
                    context.startActivity(chose);
                }
            });

            buttonBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("https://tickets.fc-zenit.ru/football/tickets/");
                    context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            });
        }
    }
}
