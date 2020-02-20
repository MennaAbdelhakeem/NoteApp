package com.example.appnote

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

        notesAdapter.onDeleteItemClickListener=
            object :NotesAdapter.OnItemClickListener{


                override fun onItemClick(position: Int, item: Note?) {
                   // val title =item?.title.toString()
                   // val description =item?.description.toString()
                   // val date =item?.time.toString()

                   // val note= Note(title = title , description = description ,time = date)

                     //remove from database
                    MyDataBase.getInstance(applicationContext)?.notesDao()?.deleteNote(notesAdapter.getNoteAt(position))

                    //remove from recyclerview
                    notesAdapter.removeItem(position)




                    // notesAdapter?.notifyItemRemoved(position)
                }
            }








        fab.setOnClickListener { view ->

            val intent =Intent(this@NoteListActivity,AddNoteActivity::class.java)
                startActivity(intent)

           // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //      .setAction("Action", null).show()
        }


val itemTouchCallback=object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT
        or ItemTouchHelper.RIGHT ){

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder): Boolean {
return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        val position = viewHolder.adapterPosition

        //remove from database
        MyDataBase.getInstance(applicationContext)?.notesDao()?.deleteNote(notesAdapter.getNoteAt(position))

        //remove from recyclerview
        notesAdapter.removeItem(position)

        Snackbar.make(viewHolder.itemView, "you are remove?", Snackbar.LENGTH_LONG)
              .setAction("Undo", View.OnClickListener {
                  notesAdapter.restoreItem(notesAdapter.getNoteAt(position),position)

                 // recyclerview.scrollToPosition(position)
                   }
              ).show()

    }
}
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)



      //////////////////////////////////////////////////////////////////////////////////////////////////





    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        val editnotePos=intent.getIntExtra(Constants.EDIT_NOTE_POSITION,-1)

        val editnotetitle=intent.getStringExtra(Constants.EDIT_NOTE_TITLE)
        val editnotedescription=intent.getStringExtra(Constants.EDIT_NOTE_DESCRIPTION)
        val editnotedate=intent.getStringExtra(Constants.EDIT_NOTE_DATE)

        val note= Note(title = editnotetitle , description = editnotedescription ,time = editnotedate)


        MyDataBase.getInstance(applicationContext)?.notesDao()?.updateNote(note)


        notesAdapter.changeData(data as MutableList<Note>?)


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

        notesAdapter.changeData(data as MutableList<Note>?)











    }




}
