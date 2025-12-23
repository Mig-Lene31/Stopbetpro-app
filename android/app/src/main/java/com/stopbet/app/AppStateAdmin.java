package com.stopbet.app;

import android.content.Context;

public class AppStateAdmin {

    private static final String PREFS = "stopbet_state";
    private static final String KEY_RELEASED_ID = "released_id";

    public static boolean isReleased(Context ctx) {
        String myId = AppState.getUserId(ctx);
        String releasedId = ctx
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getString(KEY_RELEASED_ID, null);

        return releasedId != null && releasedId.equals(myId);
    }

    public static void releaseById(Context ctx, String id) {
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_RELEASED_ID, id)
                .apply();
    }
}
