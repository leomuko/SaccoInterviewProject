package com.example.sacco.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "savings_table")
public class Savings {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "savingsId")
    private int savingsId ;

    @NonNull
    @ColumnInfo(name = "amountDeposited")
    private Integer amountDeposited;


    @NonNull
    @ColumnInfo(name = "cycle")
    private Integer cycle;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @NonNull
    @ColumnInfo(name = "userId")
    private String UserId;

    public Savings() {
    }

    public Savings(@NonNull Integer savingsId, @NonNull Integer amountDeposited, @NonNull Integer cycle, @NonNull String date, @NonNull String userId) {
        this.savingsId = savingsId;
        this.amountDeposited = amountDeposited;
        this.cycle = cycle;
        this.date = date;
        UserId = userId;
    }

    @NonNull
    public Integer getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(@NonNull Integer savingsId) {
        this.savingsId = savingsId;
    }

    @NonNull
    public Integer getAmountDeposited() {
        return amountDeposited;
    }

    public void setAmountDeposited(@NonNull Integer amountDeposited) {
        this.amountDeposited = amountDeposited;
    }

    @NonNull
    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(@NonNull Integer cycle) {
        this.cycle = cycle;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getUserId() {
        return UserId;
    }

    public void setUserId(@NonNull String userId) {
        UserId = userId;
    }
}
