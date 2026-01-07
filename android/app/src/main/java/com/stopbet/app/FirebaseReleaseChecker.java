package com.stopbet.app;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseReleaseChecker {

    public interface Callback {
        void onResult(boolean released);
    }

    public static void check(String userId, Callback callback) {
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference("users")
                .child(userId)
                .child("released");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Boolean value = snapshot.getValue(Boolean.class);
                callback.onResult(value != null && value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                callback.onResult(false);
            }
        });
    }
}
