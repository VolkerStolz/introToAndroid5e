package com.introtoandroid.simplepermissions

import androidx.appcompat.app.AppCompatActivity

class SimpleListFragmentActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list_fragment)
    }

    public override fun onCreateOptionsMenu(menu: android.view.Menu?): kotlin.Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_list, menu)
        return true
    }

    public override fun onOptionsItemSelected(item: android.view.MenuItem): kotlin.Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
