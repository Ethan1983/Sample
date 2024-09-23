package com.sample.samples.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arthenica.mobileffmpeg.FFprobe
import java.io.File

val File.isFlacFile: Boolean get() = isFlacFile(path)

private fun isFlacFile(filePath: String): Boolean {
    val mediaInformation = FFprobe.getMediaInformation(filePath) ?: return false
    val streams = mediaInformation.streams ?: return false

    return streams
        .filter { stream -> stream.type == "audio" }
        .mapNotNull { stream -> stream.codec }
        .any { codec -> codec.contains("flac") }
}

@Composable
fun AudioInfo(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        var path by remember { mutableStateOf("${context.getExternalFilesDir(null)}") }

        val onClickHandler = {
            val file = File(path)
            val validFile = file.isFile
            val isFlacFile = validFile && file.isFlacFile

            Toast.makeText(context, "Valid [$validFile] isFlac [$isFlacFile] Size [${file.length()}]", Toast.LENGTH_SHORT).show()
        }

        TextField(value = path, onValueChange = { newPath -> path = newPath })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onClickHandler) {
            Text("Check")
        }
    }
}
