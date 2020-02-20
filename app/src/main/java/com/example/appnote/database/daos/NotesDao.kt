package com.example.appnote.database.daos

import androidx.room.*
import com.example.appnote.database.model.Note

@Dao
interface NotesDao {
    @Insert
    fun insertNote(note: Note)
    @Delete
    fun deleteNote(note: Note)
    @Query("delete  from Note where id= :id")
    fun deleteNoteById(id:Int)
    @Update
    fun updateNote(note: Note)
    @Query("select * from Note")
    fun getAllNote():List<Note>
    @Query("select * from Note where description like :word")
    fun searchNotesByDescription(word:String):List<Note>
}