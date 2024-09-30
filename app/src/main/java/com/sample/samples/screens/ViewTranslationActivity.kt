package com.sample.samples.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.samples.R

class ViewTranslationActivity : AppCompatActivity() {

    private var currentTranslationX = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_translation)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<View>(R.id.text)

        textView.setOnClickListener {
            Toast.makeText(this, "Text View clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.previous).setOnClickListener {
            currentTranslationX -= 20
            textView.translationX = currentTranslationX
        }

        findViewById<View>(R.id.next).setOnClickListener {
            currentTranslationX += 20
            textView.translationX = currentTranslationX
        }
    }
}
