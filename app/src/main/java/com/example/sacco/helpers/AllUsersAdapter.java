package com.example.sacco.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sacco.R;
import com.example.sacco.models.User;

import java.util.List;

public class AllUsersAdapter extends RecyclerView.Adapter<AllUsersAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<User> mUserList;

    public AllUsersAdapter(Context context, List<User> userList) {
        mContext = context;
        mLayoutInflater =  LayoutInflater.from(mContext);
        mUserList = userList;
    }

    @NonNull
    @Override
    public AllUsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = mLayoutInflater.inflate(R.layout.all_users_item, parent, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllUsersAdapter.MyViewHolder holder, int position) {
        holder.userName.setText(mUserList.get(position).getFirstName() + " " + mUserList.get(position).getLastName());
        holder.userSavings.setText(String.valueOf(mUserList.get(position).getSavings()));
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public TextView userSavings;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.item_userName);
            userSavings = itemView.findViewById(R.id.itemUserSavings);
        }
    }
}
