package com.example.androidtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.example.androidtask.features.catslist.presentation.view.CatsImagesScreen
import com.example.androidtask.features.catslist.presentation.viewmodel.CatImageViewModel
import com.example.androidtask.ui.theme.AndroidTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val catImageViewModel: CatImageViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            AndroidTaskTheme {
                Scaffold(topBar = {
                    TopAppBar(title = { Text(" Cats Screen") }, navigationIcon = {
                        IconButton(onClick = { /* "Open nav drawer" */ }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    })
                }, floatingActionButtonPosition = FabPosition.End, floatingActionButton = {
                    ExtendedFloatingActionButton(onClick = {
                        for (i in 1..10) catImageViewModel.loadCatImagesList(i)
                    }) { Text("Refresh Cats") }
                }) { innerPadding ->
                    CatsImagesScreen(
                        modifier = Modifier
                            .consumeWindowInsets(innerPadding)
                            .padding(),
                        contentPadding = innerPadding,
                        catImageViewModel
                    )
                }
            }
        }
    }
}