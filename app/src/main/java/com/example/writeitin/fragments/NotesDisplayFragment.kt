package com.example.yourapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.writeitin.R

class NotesDisplayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve data from arguments
        val title = arguments?.getString("title") ?: "Default Title"
        val description = arguments?.getString("description") ?: "Default Description"

        // Find and set the text views
        view.findViewById<TextView>(R.id.notetitle).text= title
        view.findViewById<TextView>(R.id.notedescription).text = description
    }


    companion object {
        fun newInstance(title: String, description: String): NotesDisplayFragment {
            val fragment = NotesDisplayFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }
}
