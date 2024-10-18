package com.sample.samples.screens

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.TouchDelegate
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sample.samples.R


class TouchDelegateActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_touch_delegate)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            Toast.makeText(this@TouchDelegateActivity, "Button Clicked", Toast.LENGTH_SHORT).show()
        }

        increaseTapArea(button)
    }

    private fun increaseTapArea(view: View) {

        val parent = view.parent as View
        parent.post {
            val delegateArea = Rect()
            view.getHitRect(delegateArea)

            delegateArea.top = -delegateArea.top
            delegateArea.left = -delegateArea.left
            delegateArea.bottom += parent.height - delegateArea.bottom
            delegateArea.right += parent.width - delegateArea.right

            parent.touchDelegate = TouchDelegate(delegateArea, view)
        }

    }
}
