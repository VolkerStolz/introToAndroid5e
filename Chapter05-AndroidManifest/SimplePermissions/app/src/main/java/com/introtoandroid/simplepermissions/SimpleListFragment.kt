package com.introtoandroid.simplepermissions

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.ListFragment
import androidx.loader.content.CursorLoader


class SimpleListFragment : ListFragment(), AdapterView.OnItemClickListener {
    public override fun onCreateView(
        inflater: LayoutInflater, container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): android.view.View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_list, container, false)
    }

    public override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val requestedColumns = kotlin.arrayOf<kotlin.String?>(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
        val loader: CursorLoader = CursorLoader(
            requireActivity(),
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            requestedColumns, null, null, null
        )
        val contacts: android.database.Cursor? = loader.loadInBackground()

        val adapter: android.widget.ListAdapter = SimpleCursorAdapter(
            activity,
            R.layout.contact_list_simple,
            contacts,
            arrayOf<String>(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            ),
            intArrayOf(
                R.id.contact_item_simple_text
            ), 0
        )

        setListAdapter(adapter)

        getListView().setOnItemClickListener(this)
    }

    @SuppressLint("Range")
    override fun onItemClick(
        parent: AdapterView<*>, view: android.view.View?, position: kotlin.Int,
        id: kotlin.Long
    ) {
        val phone = parent.getItemAtPosition(position) as android.database.Cursor

        val tv = (view as android.widget.TextView)
        val name = phone.getString(
            phone.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
        )
        val num = phone.getString(
            phone.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )
        )

        val displayed = tv.getText().toString()
        if (displayed.compareTo(name) == 0) {
            tv.setText(num)
        } else {
            tv.setText(name)
        }
        android.util.Log.d(
            SimpleListFragment.Companion.DEBUG_TAG, "Cursor pos: " +
                    phone.getPosition() + "== list pos: " + position
        )
        android.util.Log.d(
            SimpleListFragment.Companion.DEBUG_TAG, "Cursor id: " +
                    phone.getString(
                        phone.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone._ID
                        )
                    ) +
                    "== list id: " + id
        )
    }

    companion object {
        const val DEBUG_TAG: kotlin.String = "SimpleListFragment"
    }
}
