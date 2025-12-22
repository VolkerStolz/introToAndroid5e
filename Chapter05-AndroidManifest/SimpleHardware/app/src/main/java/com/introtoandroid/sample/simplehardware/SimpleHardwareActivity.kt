package com.introtoandroid.sample.simplehardware

import android.view.Menu
import android.view.MenuItem

class SimpleHardwareActivity : MenuActivity() {
    override fun prepareMenu() {
        addMenuItem("1. Sensors Sample", SensorsActivity::class.java)
        addMenuItem("2. Battery Monitor", BatteryActivity::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_hardware, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
