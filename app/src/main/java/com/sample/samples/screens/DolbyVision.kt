package com.sample.samples.screens

import android.content.Context
import android.hardware.display.DisplayManager
import android.media.MediaCodecList
import android.media.MediaFormat
import android.view.Display
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun DolbyVisionSupport(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    val displays = displayManager.displays

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        item {
            Text("isDolbyVisionDecoderPresent [$isDolbyVisionDecoderPresent]")
        }

        items(displays.size) { index ->
            val display = displays[index]

            Spacer(modifier = Modifier.padding(8.dp))

            Text("Display [${display.name}] isDolbyVisionSupported [${display.isDolbyVisionSupported}]")

            Spacer(modifier = Modifier.padding(8.dp))

            Text("Display [${display.name}] isHDR10Supported [${display.isHDR10Supported}]")

            Spacer(modifier = Modifier.padding(8.dp))

            Text("Display [${display.name}] isHDRHLGSupported [${display.isHybridLogGammaHDRSupported}]")
        }
    }
}

private val isDolbyVisionDecoderPresent
    get(): Boolean {
        val mediaCodecList = MediaCodecList(MediaCodecList.REGULAR_CODECS)

        val format = MediaFormat().apply {
            setString(MediaFormat.KEY_MIME, MediaFormat.MIMETYPE_VIDEO_DOLBY_VISION)
        }

        return mediaCodecList.findDecoderForFormat(format) != null
    }

private val Display.isDolbyVisionSupported
    get(): Boolean {
        return Display.HdrCapabilities.HDR_TYPE_DOLBY_VISION in hdrCapabilities.supportedHdrTypes
    }

private val Display.isHDR10Supported
    get(): Boolean {
        return Display.HdrCapabilities.HDR_TYPE_HDR10 in hdrCapabilities.supportedHdrTypes
    }

private val Display.isHybridLogGammaHDRSupported
    get(): Boolean {
        return Display.HdrCapabilities.HDR_TYPE_HLG in hdrCapabilities.supportedHdrTypes
    }