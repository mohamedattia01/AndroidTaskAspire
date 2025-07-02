package com.example.androidtask.features.catslist.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.androidtask.core.state.UiState
import com.example.androidtask.features.catslist.domain.model.CatImageModel
import com.example.androidtask.features.catslist.presentation.viewmodel.CatImageViewModel

@Composable
fun CatsImagesScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    catImageViewModel: CatImageViewModel = hiltViewModel(),
) {
    val openAlertDialog = remember { mutableStateOf(true) }
    val catImageStateValue = catImageViewModel.catImagesStateFlow.collectAsState().value
    LaunchedEffect(Unit) {
        for (i in 1..10) catImageViewModel.loadCatImagesList(i)
    }

    when (catImageStateValue) {
        is UiState.Success -> CardsComponent(
            modifier = modifier,
            contentPadding = contentPadding,
            catImageStateValue.data
        )

        is UiState.Loading -> LoadingProgress(modifier = modifier)
        is UiState.Error -> if (openAlertDialog.value) AlertDialog(
            modifier = modifier,
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
            },
            dialogTitle = catImageStateValue.errorModel.title,
            dialogText = catImageStateValue.errorModel.description,
            icon = Icons.Default.Info
        )
    }
}

@Composable
private fun CardsComponent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    dataItems: List<CatImageModel>,
) {
    LazyColumn(
        state = rememberLazyListState(), modifier = modifier, contentPadding = contentPadding
    ) {
        items(dataItems) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .padding(top = 6.dp)
                    .wrapContentHeight(align = Alignment.Top),
                shape = CutCornerShape(topEnd = 20.dp),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(it.url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}

@Composable
fun LoadingProgress(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun AlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector = Icons.Default.Info,
) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
    ) {
        AlertDialog(modifier = Modifier.align(Alignment.Center), icon = {
            Icon(icon, contentDescription = "Example Icon")
        }, title = {
            Text(text = dialogTitle)
        }, text = {
            Text(text = dialogText)
        }, onDismissRequest = {
            onDismissRequest()
        }, confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }) {
                Text("Confirm")
            }
        }, dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }) {
                Text("Dismiss")
            }
        })
    }
}