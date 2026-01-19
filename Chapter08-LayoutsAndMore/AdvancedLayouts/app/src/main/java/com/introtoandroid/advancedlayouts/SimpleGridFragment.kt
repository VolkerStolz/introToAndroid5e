package com.introtoandroid.advancedlayouts

import android.app.Fragment
import android.content.CursorLoader
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ListAdapter
import android.widget.Toast


class SimpleGridFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_simple_grid, container, false)
        val toolbar = view.findViewById<View?>(R.id.toolbar) as Toolbar
        toolbar.setTitle("Advanced Layouts")
        toolbar.setTitleTextColor(Color.WHITE)
        val activity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        checkNotNull(activity.getSupportActionBar())
        activity.getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val loader = CursorLoader(
            getActivity(),
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )
        val contacts = loader.loadInBackground()
        val adapter: ListAdapter = SimpleCursorAdapter(
            getActivity(),
            R.layout.contact_grid_simple,
            contacts,
            arrayOf<String>(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ), intArrayOf(
                R.id.scratch_text1, R.id.scratch_text2
            ), 0
        )

        val av = getActivity().findViewById<View?>(R.id.scratch_adapter_view) as GridView
        av.setAdapter(adapter)
        av.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                Toast.makeText(
                    getActivity(),
                    "Clicked _id=" + id, Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
