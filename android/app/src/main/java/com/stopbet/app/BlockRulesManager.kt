package com.stopbet.app

import android.content.Context

class BlockRulesManager private constructor(context: Context) {

    private val prefs =
        context.getSharedPreferences("STOPBET_RULES", Context.MODE_PRIVATE)

    companion object {
        private var instance: BlockRulesManager? = null

        fun getInstance(context: Context): BlockRulesManager {
            if (instance == null) {
                instance = BlockRulesManager(context.applicationContext)
            }
            return instance!!
        }

        private const val KEY_DEPOSIT = "deposit_value"
        private const val KEY_STOP_WIN = "stop_win"
        private const val KEY_STOP_LOSS = "stop_loss"
        private const val KEY_BLOCK_ACTIVE = "block_active"
        private const val KEY_BLOCK_START_TIME = "block_start_time"

        private const val BLOCK_DURATION_MS = 12 * 60 * 60 * 1000L // 12h
    }

    /* ===============================
       CONFIGURAÇÕES
       =============================== */

    fun saveDeposit(value: Double) {
        prefs.edit().putFloat(KEY_DEPOSIT, value.toFloat()).apply()
    }

    fun saveStopWin(value: Double) {
        prefs.edit().putFloat(KEY_STOP_WIN, value.toFloat()).apply()
    }

    fun saveStopLoss(value: Double) {
        prefs.edit().putFloat(KEY_STOP_LOSS, value.toFloat()).apply()
    }

    /* ===============================
       CONTROLE DO BLOQUEIO (12H)
       =============================== */

    fun activateBlock() {
        prefs.edit()
            .putBoolean(KEY_BLOCK_ACTIVE, true)
            .putLong(KEY_BLOCK_START_TIME, System.currentTimeMillis())
            .apply()
    }

    fun forceUnlock() {
        prefs.edit()
            .putBoolean(KEY_BLOCK_ACTIVE, false)
            .remove(KEY_BLOCK_START_TIME)
            .apply()
    }

    fun isBlockActive(): Boolean {
        if (!prefs.getBoolean(KEY_BLOCK_ACTIVE, false)) return false

        val startTime = prefs.getLong(KEY_BLOCK_START_TIME, 0L)
        if (startTime == 0L) return false

        val elapsed = System.currentTimeMillis() - startTime

        return if (elapsed >= BLOCK_DURATION_MS) {
            // ⏱ 12h acabaram → desbloqueia
            forceUnlock()
            false
        } else {
            true
        }
    }

    fun getRemainingTimeMs(): Long {
        val startTime = prefs.getLong(KEY_BLOCK_START_TIME, 0L)
        if (startTime == 0L) return 0L

        val elapsed = System.currentTimeMillis() - startTime
        val remaining = BLOCK_DURATION_MS - elapsed
        return if (remaining > 0) remaining else 0L
    }
}
