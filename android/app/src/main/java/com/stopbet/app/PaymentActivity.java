package com.stopbet.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String uid = AuthManager.getUid();

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(60, 60, 60, 60);
        root.setBackgroundColor(UiStyle.background());

        TextView title = new TextView(this);
        title.setText("Kairós — Aguardando Liberação");
        title.setTextSize(24);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);

        TextView id = new TextView(this);
        id.setText("\nSEU ID FIXO:\n" + uid);
        id.setTextColor(0xFFFFFFFF);
        id.setGravity(Gravity.CENTER);

        root.addView(title);
        root.addView(id);

        setContentView(root);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(uid)
                .child("released");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Boolean released = snapshot.getValue(Boolean.class);
                if (released != null && released) {
                    ReleaseState.markReleased(PaymentActivity.this);
                    startActivity(new Intent(PaymentActivity.this, GateActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}
