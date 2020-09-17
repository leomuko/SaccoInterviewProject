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

    public User() {
    }

    public User(@NonNull String userId, @NonNull String firstName, @NonNull String lastName, @NonNull String role) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Role = role;
    }

    @NonNull
    public String getUserId() {
        return UserId;
    }

    @NonNull
    public String getFirstName() {
        return FirstName;
    }

    @NonNull
    public String getLastName() {
        return LastName;
    }

    @NonNull
    public String getRole() {
        return Role;
    }

    public void setUserId(@NonNull String userId) {
        UserId = userId;
    }

    public void setFirstName(@NonNull String firstName) {
        FirstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        LastName = lastName;
    }

    public void setRole(@NonNull String role) {
        Role = role;
    }
}
