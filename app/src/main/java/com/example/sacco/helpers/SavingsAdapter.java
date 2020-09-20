package com.example.sacco.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sacco.R;
import com.example.sacco.models.Savings;

import java.util.List;

public class SavingsAdapter extends RecyclerView.Adapter<SavingsAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Savings> mSavings;

    public SavingsAdapter(Context context, List<Savings> savings) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mSavings = savings;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.savings_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setText(mSavings.get(position).getDate());
        holder.amount.setText(String.valueOf(mSavings.get(position).getAmountDeposited()));
    }

    @Override
    public int getItemCount() {
        return mSavings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView amount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.item_date);
            amount = itemView.findViewById(R.id.item_price);
        }
    }
}
