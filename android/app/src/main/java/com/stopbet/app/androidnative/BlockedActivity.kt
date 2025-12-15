package com.stopbet.app.androidnative

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class BlockedActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this)
        textView.text = "🚫 Acesso bloqueado pelo StopBet Pro"
        textView.textSize = 20f
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        setContentView(textView)
    }

    override fun onBackPressed() {
        // Impede voltar
    }
}
