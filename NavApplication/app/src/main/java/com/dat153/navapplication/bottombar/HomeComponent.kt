package com.dat153.navapplication.bottombar

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dat153.navapplication.bottombar.datatypes.Contact
import com.dat153.navapplication.fullscreen.FullscreenActivity



@Composable
fun HomeComponent(modifier: Modifier, contacts : List<Contact>, onContactPick: (Int) -> Unit){

    val context = LocalContext.current
    Column(modifier) {
        Text("I'm the home component!")
        Button(onClick = {
            val intent = Intent(context, FullscreenActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Go to Full Screen")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)

        ) {
            items(contacts.filter { it.id != 0 }) { contact ->
                ContactItem(contact = contact, onClick = onContactPick)
            }
        }
    }

}

@Composable
fun ContactItem(contact: Contact, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(contact.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = contact.name)
        }
    }
}
