package com.example.writeitin.Adapters

import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.writeitin.R
import com.example.writeitin.models.NoteModel

class NoteListsRecyclerViewAdapter(private val notes:MutableList<NoteModel>, val listener: OnItemClickListener) : RecyclerView.Adapter<NoteListsRecyclerViewAdapter.ViewHolder>(){

     interface OnItemClickListener {
        fun onItemClick(item: NoteModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        Log.d("debug","oncreate")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.not_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("debug","onBind")
        val itemsViewModel = notes[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.title.text= itemsViewModel.title


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return notes.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val title: TextView = itemView.findViewById(R.id.title)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(notes[position])
                }
            }
        }

    }



}