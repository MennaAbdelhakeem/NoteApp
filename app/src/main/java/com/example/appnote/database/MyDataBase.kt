package com.example.appnote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appnote.database.daos.NotesDao
import com.example.appnote.database.model.Note

@Database(entities = arrayOf(Note::class),exportSchema = false,version = 1)
abstract class MyDataBase:RoomDatabase() {

    abstract fun notesDao(): NotesDao
    companion object{

        private  var MY_DATABASE_INSTANCE:MyDataBase?=null
        fun getInstance(context: Context):MyDataBase?{

            if (MY_DATABASE_INSTANCE==null){
                MY_DATABASE_INSTANCE = Room.databaseBuilder(context
                    ,MyDataBase::class.java,
                    "NotesDataBase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return MY_DATABASE_INSTANCE
        }
    }
}