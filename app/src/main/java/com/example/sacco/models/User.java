package com.example.sacco.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userId")
    private String UserId;
    @NonNull
    @ColumnInfo(name = "firstName")
    private String FirstName;
    @NonNull
    @ColumnInfo(name = "lastName")
    private String LastName;
    @NonNull
    @ColumnInfo(name = "role")
    private String Role;
    @NonNull
    @ColumnInfo(name = "savings")
    private Integer Savings;

    public User() {
    }

    public User(@NonNull String userId, @NonNull String firstName, @NonNull String lastName, @NonNull String role, @NonNull Integer savings) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Role = role;
        Savings = savings;
    }

    @NonNull
    public String getUserId() {
        return UserId;
    }

    public void setUserId(@NonNull String userId) {
        UserId = userId;
    }

    @NonNull
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(@NonNull String firstName) {
        FirstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return LastName;
    }

    public void setLastName(@NonNull String lastName) {
        LastName = lastName;
    }

    @NonNull
    public String getRole() {
        return Role;
    }

    public void setRole(@NonNull String role) {
        Role = role;
    }

    @NonNull
    public Integer getSavings() {
        return Savings;
    }

    public void setSavings(@NonNull Integer savings) {
        Savings = savings;
    }
}
