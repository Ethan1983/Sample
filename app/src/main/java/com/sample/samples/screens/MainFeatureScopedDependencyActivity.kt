package com.sample.samples.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sample.samples.R
import com.sample.samples.repository.SampleRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFeatureScopedDependencyActivity : AppCompatActivity() {

    @Inject
    lateinit var sampleRepository: SampleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main_feature_scoped_dependency)

        findViewById<TextView>(R.id.text).apply {
            text = sampleRepository.getSampleValue()
        }

        findViewById<View>(R.id.button).setOnClickListener {
            startActivity(
                Intent(
                    this@MainFeatureScopedDependencyActivity,
                    SubFeatureScopedDependencyActivity::class.java
                )
            )
        }
    }
}
