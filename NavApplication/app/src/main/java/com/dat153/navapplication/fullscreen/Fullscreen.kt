package com.dat153.navapplication.fullscreen

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.dat153.navapplication.R
import com.dat153.navapplication.bottombar.MainActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullscreenComponent(modifier: Modifier) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Full Screen") },
                navigationIcon = {
                    IconButton(onClick = {
                        (context as? FullscreenActivity)?.finish()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(text = "This is the full-screen component", modifier = Modifier.padding(innerPadding))
    }
}