package com.example.writeitin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.writeitin.Adapters.NoteListsRecyclerViewAdapter
import com.example.writeitin.models.NoteModel

class MainActivity : AppCompatActivity(), NoteListsRecyclerViewAdapter.OnItemClickListener {



    private lateinit var adapter : NoteListsRecyclerViewAdapter
    private lateinit var recycle : RecyclerView

    private val editNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == RESULT_OK) {

            val updatedNote = result.data?.getSerializableExtra("updated_note") as? NoteModel
            Toast.makeText(this,updatedNote?.title,Toast.LENGTH_LONG)
            if (updatedNote != null) {

                val position = result.data?.getIntExtra("id",0) //objects.indexOfFirst { it.title == updatedNote.title }
                Log.d("hello","${position}")
                if (position != -1) {
                    Log.d("hello","${position}")
                    objects[position!!-1] = updatedNote
                    adapter.notifyItemChanged(position-1)
                }
            }
        }
    }

    private val objects = mutableListOf(
        NoteModel(1,"Daily Tasks","do nothing today : checked"),
        NoteModel(2,"tomorrow Tasks","do nothing tomorrow : checked"),
        NoteModel(3,"yesterday Tasks","did nothing yesterday : checked"),
        NoteModel(4,"hehe Tasks","do nothing ever : checked"),
        NoteModel(5,"Grocery Shopping", "Buy milk, eggs, bread, and cheese from the supermarket."),
        NoteModel(6,"Workout", "Go for a 30-minute run and do a 15-minute strength training routine."),
        NoteModel(7,"Meeting with Team", "Discuss project progress and upcoming deadlines at 10 AM."),
        NoteModel(8,"Read Book", "Finish reading 'Atomic Habits' by James Clear."),
        NoteModel(9,"Cook Dinner", "Prepare spaghetti carbonara for dinner."),
        NoteModel(10,"Clean House", "Vacuum the living room and clean the bathroom."),
        NoteModel(11,"Write Blog Post", "Draft a new blog post on the benefits of meditation."),
        NoteModel(12,"Call Parents", "Have a catch-up call with mom and dad in the evening."),
        NoteModel(13,"Plan Vacation", "Research and plan itinerary for the upcoming trip to Italy."),
        NoteModel(14,"Complete Assignment", "Finish the math assignment due tomorrow.")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }


    fun init(){
        recycle = findViewById(R.id.ReView)
        recycle.layoutManager = LinearLayoutManager(this)


        adapter = NoteListsRecyclerViewAdapter(objects,this)
        recycle.adapter = adapter
    }

    fun loadDisplay(note : NoteModel){

        val intent = Intent(this , NotesLoader::class.java)
        intent.putExtra("note",note)
        editNoteLauncher.launch(intent)
    }

    override fun onItemClick(item: NoteModel) {
        val card = findViewById<CardView>(R.id.myCard)
        card.setCardBackgroundColor(Color.BLACK)
        Log.d("listener","hehe")
        loadDisplay(item)
    }



}