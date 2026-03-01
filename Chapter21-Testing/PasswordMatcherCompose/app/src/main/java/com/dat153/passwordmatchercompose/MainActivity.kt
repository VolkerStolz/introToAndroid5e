package com.dat153.passwordmatchercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordMatcherScreen()
        }
    }
}

@Composable
fun PasswordMatcherScreen() {
    var password by remember { mutableStateOf("") }
    var matchingPassword by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf<String?>(null) }
    var resultColor by remember { mutableStateOf(Color.Unspecified) }
    var resultSemantic by remember { mutableStateOf("no_notice") }

    Column(
        modifier = Modifier
            .semantics {
                contentDescription = "Password Screen"
            }
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Match Passwords",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("title")
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Password")
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("password")
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Confirm Password")
        OutlinedTextField(
            value = matchingPassword,
            onValueChange = { matchingPassword = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("matchingPassword")
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (password.isNotEmpty() && password == matchingPassword) {
                    resultMessage = "Passwords match!"
                    resultColor = Color(0xFF4CAF50)
                    resultSemantic = "match_password_notice"
                } else {
                    resultMessage = "Passwords do not match."
                    resultColor = Color(0xFFF44336)
                    resultSemantic = "non_matching_password_notice"
                }
            },
            modifier = Modifier.testTag("matchButton").fillMaxWidth()
        ) {
            Text("Check Passwords")
        }

        Spacer(modifier = Modifier.height(16.dp))

        resultMessage?.let { message ->
            Text(
                text = message,
                color = resultColor,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                            .semantics{
                                contentDescription = "result_message"
                                stateDescription = resultSemantic
                            }
                            .testTag("passwordResult")
            )
        }
    }
}