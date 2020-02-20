package com.example.appnote

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.appnote.database.MyDataBase
import com.example.appnote.database.model.Note
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        Button_add.setOnClickListener( {

            val titletext=text_title.text.toString()
            val descriptiontext=text_description.text.toString()
            val datetext=text_time.text.toString()

            val note= Note(title = titletext , description = descriptiontext ,time = datetext)

            MyDataBase.getInstance(applicationContext)?.notesDao()?.insertNote(note)
            showSuccessMessage()
        })




    }

    fun showSuccessMessage(){

        val builder=AlertDialog.Builder(this)
        builder.setMessage(R.string.notecreatesuccessfully)
        builder.setPositiveButton(R.string.ok,DialogInterface.OnClickListener {
                dialog, which ->

            dialog.dismiss()
            finish()
        })

        builder.setCancelable(false)
        builder.show()
    }
}
