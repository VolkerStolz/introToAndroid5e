package com.introtoandroid.advancedlayouts

import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ListFragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListAdapter
import android.widget.TextView


class SimpleListFragment : ListFragment(), OnItemClickListener,
    LoaderManager.LoaderCallbacks<Cursor?> {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_simple_list, container, false)
        val toolbar = view.findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitle("Advanced Layouts")
        toolbar.setTitleTextColor(Color.WHITE)
        val activity = getActivity() as AppCompatActivity?
        activity!!.setSupportActionBar(toolbar)
        checkNotNull(activity.getSupportActionBar())
        activity.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        Log.v(DEBUG_TAG, "onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val requestedColumns = arrayOf<String?>(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
        val loader = CursorLoader(
            getActivity()!!,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            requestedColumns, null, null, null
        )
        val contacts = loader.loadInBackground()

        val adapter: ListAdapter = SimpleCursorAdapter(
            getActivity(),
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

    override fun onItemClick(
        parent: AdapterView<*>, view: View?, position: Int,
        id: Long
    ) {
        val phone = parent.getItemAtPosition(position) as Cursor

        val tv = (view as TextView)
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
        Log.v(
            DEBUG_TAG, "Cursor pos: " +
                    phone.getPosition() + "== list pos: " + position
        )
        Log.v(
            DEBUG_TAG, "Cursor id: " +
                    phone.getString(
                        phone.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone._ID
                        )
                    ) +
                    "== list id: " + id
        )
    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor?> {
        val requestedColumns = arrayOf<String?>(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
        val loader = CursorLoader(
            getActivity()!!,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            requestedColumns, null, null, null
        )
        val contacts = loader.loadInBackground()
        val adapter: ListAdapter = SimpleCursorAdapter(
            getActivity(),
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
        Log.v(DEBUG_TAG, "First contact loaded: ")

        initLoader<Cursor?>(loader.getId(), bundle, this, getLoaderManager())

        //        loader.startLoading();
//        getLoaderManager().getLoader(loader.getId()).startLoading();
        return loader
    }


    /**
     * Dislays either the name of the first contact or a message.
     */
    override fun onLoadFinished(loader: Loader<Cursor?>, cursor: Cursor?) {
        Log.v(DEBUG_TAG, "First contact loaded: ")


        if (cursor != null) {
            val totalCount = cursor.getCount()
            if (totalCount > 0) {
                cursor.moveToFirst()
                val name = cursor
                    .getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                //                mMessageText.setText(
//                        getResources().getString(R.string.contacts_string, totalCount, name));
                val adapter: ListAdapter = SimpleCursorAdapter(
                    getActivity(),
                    R.layout.contact_list_simple,
                    cursor,
                    arrayOf<String>(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    ),
                    intArrayOf(
                        R.id.contact_item_simple_text
                    ), 0
                )

                setListAdapter(adapter)

                getListView().setOnItemClickListener(this)
                Log.v(DEBUG_TAG, "First contact loaded: ")
                Log.v(DEBUG_TAG, "Total number of contacts: " + totalCount)
            } else {
                Log.v(DEBUG_TAG, "List of contacts is empty.")
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor?>) {
        // Implement
        loader.forceLoad()
    }

    companion object {
        const val DEBUG_TAG: String = "SimpleListFragment"

        fun <T> initLoader(
            loaderId: Int, args: Bundle?, callbacks: LoaderManager.LoaderCallbacks<T?>,
            loaderManager: LoaderManager
        ) {
            val loader = loaderManager.getLoader<T?>(loaderId)
            if (loader != null && loader.isReset()) {
                loaderManager.restartLoader<T?>(loaderId, args, callbacks)
            } else {
                loaderManager.initLoader<T?>(loaderId, args, callbacks)
            }
        }
    }
}
