package com.stopbet.app;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseAccessStore {

    public interface Callback {
        void onResult(boolean unlocked);
    }

    public static void checkAccess(Context ctx, String userId, Callback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
          .document(userId)
          .get()
          .addOnSuccessListener(doc -> {
              if (!doc.exists()) {
                  callback.onResult(false);
                  return;
              }

              Boolean unlocked = doc.getBoolean("unlocked");
              Long until = doc.getLong("until");

              if (unlocked == null || until == null) {
                  callback.onResult(false);
                  return;
              }

              boolean valid = unlocked && System.currentTimeMillis() < until;
              callback.onResult(valid);
          })
          .addOnFailureListener(e -> callback.onResult(false));
    }
}
