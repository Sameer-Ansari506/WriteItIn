package com.example.writeitin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.writeitin.R
import com.example.writeitin.models.NoteModel
import com.example.yourapp.NotesDisplayFragment

class NoteEditFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.note_edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve data from arguments
        var note = arguments?.getSerializable("note") as? NoteModel

//        // Find and set the text views
       view.findViewById<EditText>(R.id.edittitle).hint= note?.title
        view.findViewById<EditText>(R.id.editdescription).hint = note?.description

    }

//    fun setText(){
//
//
//    }



    companion object {
        fun newInstance(note : NoteModel): NoteEditFragment {
            val fragment = NoteEditFragment()
            val args = Bundle()
            args.putSerializable("note1",note)
            fragment.arguments = args
            return fragment
        }
    }
}