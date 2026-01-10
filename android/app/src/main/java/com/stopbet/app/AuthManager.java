package com.stopbet.app;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthManager {

    public interface AuthCallback {
        void onReady(String uid);
    }

    public static void ensureAuth(AuthCallback callback) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            callback.onReady(user.getUid());
            return;
        }

        auth.signInAnonymously()
                .addOnSuccessListener(result ->
                        callback.onReady(result.getUser().getUid())
                );
    }
}
