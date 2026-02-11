package com.dat153.navapplication.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailsComponent(modifier: Modifier, contactId: Int, navController: NavController){
    Column(modifier) {
        val contact = contacts.find { it.id == contactId }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = contact?.name ?: "Contact not found")
                Text(text = contact?.phoneNumber ?: "")
            }
        }
        Button({navController.popBackStack()}) {
            Text("Back")
        }
    }
}