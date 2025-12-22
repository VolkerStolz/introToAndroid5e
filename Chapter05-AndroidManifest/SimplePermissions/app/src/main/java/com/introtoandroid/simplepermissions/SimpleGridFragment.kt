package com.introtoandroid.simplepermissions

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.GridView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.loader.content.CursorLoader


class SimpleGridFragment : android.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): android.view.View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_grid, container, false)
    }

    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val loader: CursorLoader = CursorLoader(
            activity,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )
        val contacts: android.database.Cursor? = loader.loadInBackground()
        val adapter: android.widget.ListAdapter = SimpleCursorAdapter(
            activity,
            R.layout.contact_grid_simple,
            contacts,
            arrayOf<String>(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), intArrayOf(
                R.id.scratch_text1, R.id.scratch_text2
            ), 0
        )

        val view_id: kotlin.Int = R.id.scratch_adapter_view
        val av: GridView = activity.findViewById<android.view.View?>(view_id) as GridView
        av.setAdapter(adapter)
        av.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: android.view.View?, position: kotlin.Int, id: kotlin.Long
            ) {
                Toast.makeText(
                    activity,
                    "Clicked _id=" + id, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
