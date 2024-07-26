package com.example.writeitin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.writeitin.fragments.NoteEditFragment
import com.example.writeitin.models.NoteModel
import com.example.yourapp.NotesDisplayFragment


class NotesLoader : AppCompatActivity() {

    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var editBtn : ImageButton
    private lateinit var backBtn : ImageButton
    private var mode : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_notes_loader)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("secomd","meow" )

        init()
    }



    fun init() {

        Log.d("here","in")
        editBtn = findViewById(R.id.edit)
        backBtn = findViewById(R.id.back)
        val note = intent.getSerializableExtra("note") as? NoteModel

        if (note != null) {

            val fragment = NotesDisplayFragment.newInstance(note.title, note.description)

            val editFragment = NoteEditFragment.newInstance(note)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment,fragment)
                .commit()

            editBtn.setOnClickListener {

                if( mode == false) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, editFragment)
                        .commit()

                    editBtn.setImageResource(R.drawable.check)
                    editBtn.setBackgroundColor(Color.BLACK)
                    mode = true
                }
                else {
                    val editTitle = findViewById<EditText>(R.id.edittitle)
                    val editDesc = findViewById<EditText>(R.id.editdescription)
                    note.title = editTitle.text.toString()
                    note.description = editDesc.text.toString()
                    val updatedFragment = NotesDisplayFragment.newInstance(note.title, note.description)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, updatedFragment)
                        .commit()


                    editBtn.setImageResource(R.drawable.edit)
                    mode = false
                }
            }

            backBtn.setOnClickListener {

                val resultIntent = Intent()
                resultIntent.putExtra("updated_note", note)
                resultIntent.putExtra("id",note.id)
                setResult(RESULT_OK, resultIntent)
                finish()
            }

//           listeners()
        } else {

            Log.e("NotesLoader", "Note data is missing!")
        }


    }

//    fun listeners(fragment: NotesDisplayFragment, editFragment: NoteEditFragment){
//
//        editBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment,editFragment)
//                .commit()
//        }
//        doneBtn.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment,fragment)
//                .commit()
//
//        }
//    }


}