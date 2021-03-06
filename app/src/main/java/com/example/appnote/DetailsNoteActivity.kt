package com.example.appnote

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.appnote.database.MyDataBase
import com.example.appnote.database.model.Note
import kotlinx.android.synthetic.main.activity_details_note.*

class DetailsNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_note)

        val notePos=intent.getIntExtra(Constants.NOTE_POSITION,-1)
        val notetitle=intent.getStringExtra(Constants.NOTE_TITLE)
        val notedescription=intent.getStringExtra(Constants.NOTE_DESCRIPTION)
        val notedate=intent.getStringExtra(Constants.NOTE_DATE)
        edittext_title.setText(notetitle)
        edittext_description.setText(notedescription)
        edittext_date.setText(notedate)


        Button_update.setOnClickListener ({
            val title =edittext_title.text.toString()
            val description =edittext_description.text.toString()
            val date =edittext_date.text.toString()
            val notePos=intent.getIntExtra(Constants.NOTE_POSITION,-1)


            val intent = Intent(this, NoteListActivity::class.java)
            intent.putExtra(Constants.EDIT_NOTE_TITLE,title)
            intent.putExtra(Constants.EDIT_NOTE_DESCRIPTION,description)
            intent.putExtra(Constants.EDIT_NOTE_DATE,date)
            intent.putExtra(Constants.EDIT_NOTE_POSITION,notePos)




            showSuccessMessage()

        })

    }

    fun showSuccessMessage(){

        val builder= AlertDialog.Builder(this)
        builder.setMessage(R.string.noteupdatesuccessfully)
        builder.setPositiveButton(R.string.ok, DialogInterface.OnClickListener {
                dialog, which ->

            dialog.dismiss()
            finish()
        })

        builder.setCancelable(false)
        builder.show()
    }
}
