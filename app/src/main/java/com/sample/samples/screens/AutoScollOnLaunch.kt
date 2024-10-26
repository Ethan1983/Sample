package com.sample.samples.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AutoScrollList(modifier: Modifier = Modifier) {
    val items = (1..1000).map { it }
    var scrollTo by remember { mutableIntStateOf((0 until items.size).random()) }
    var scrollToOffset by remember { mutableIntStateOf(0) }
    var firstVisibleItemIndex by remember { mutableIntStateOf(0) }
    var firstVisibleItemScrollOffset by remember { mutableIntStateOf(0) }

    var previousScrollHandler = {
        scrollTo = firstVisibleItemIndex
        scrollToOffset = firstVisibleItemScrollOffset
    }

    val randomScrollHandler = {
        scrollTo = (0 until items.size).random()
    }

    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(randomScrollHandler) {
            Text("Scroll to random item")
        }

        Spacer(Modifier.height(16.dp))

        Button(previousScrollHandler) {
            Text("Scroll to last selected item")
        }

        Spacer(Modifier.height(16.dp))

        ScrollableList(
            items,
            scrollTo,
            scrollToOffset
        ) { firstVisibleIndex, firstVisibleItemOffset ->
            firstVisibleItemIndex = firstVisibleIndex
            firstVisibleItemScrollOffset = firstVisibleItemOffset
        }
    }
}

@Composable
private fun ScrollableList(
    items: List<Int>,
    scrollTo: Int,
    scrollToOffset: Int,
    modifier: Modifier = Modifier,
    onItemSelected: (Int, Int) -> Unit
) {
    val state = rememberLazyListState()

    LaunchedEffect(state, scrollTo) {
        state.scrollToItem(scrollTo, scrollToOffset)
    }

    LazyColumn(
        state = state,
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items.size, key = { it }) {
            Text(
                "Item is ${items[it]}",
                style = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onItemSelected(
                            state.firstVisibleItemIndex,
                            state.firstVisibleItemScrollOffset
                        )
                    }
            )
        }
    }
}
