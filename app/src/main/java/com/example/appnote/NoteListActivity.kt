package com.example.appnote

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.appnote.adapter.NotesAdapter
import com.example.appnote.database.MyDataBase
import com.example.appnote.database.model.Note

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {
var notesAdapter=NotesAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        recyclerview.adapter=notesAdapter

      notesAdapter.onItemClickListener=
    object :NotesAdapter.OnItemClickListener{

        override fun onItemClick(position: Int, item: Note?) {
            startDetailsNoteActivity(position,item)

        }
}

        fab.setOnClickListener { view ->

            val intent =Intent(this@NoteListActivity,AddNoteActivity::class.java)
                startActivity(intent)

           // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //      .setAction("Action", null).show()
        }
    }

    private fun startDetailsNoteActivity(position: Int, item: Note?) {

        val intent = Intent(this, DetailsNoteActivity::class.java)
        intent.putExtra(Constants.NOTE_TITLE,item?.title)
        intent.putExtra(Constants.NOTE_DATE,item?.time)
        intent.putExtra(Constants.NOTE_DESCRIPTION,item?.description)
        intent.putExtra(Constants.NOTE_POSITION,position)

        startActivity(intent)

    }

    override fun onStart() {
        super.onStart()

        val data=MyDataBase.getInstance(applicationContext)?.notesDao()?.getAllNote()

        notesAdapter.changeData(data)
    }
}
