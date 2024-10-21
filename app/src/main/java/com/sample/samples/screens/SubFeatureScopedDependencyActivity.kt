package com.sample.samples.screens

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sample.samples.repository.SampleRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubFeatureScopedDependencyActivity : AppCompatActivity() {

    @Inject
    lateinit var sampleRepository: SampleRepository

    @Inject
    lateinit var sampleRepository2: SampleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val textView = TextView(this).apply {
            text = "${sampleRepository.getSampleValue()} \n ${sampleRepository2.getSampleValue()}"
        }

        setContentView(textView)
    }
}
