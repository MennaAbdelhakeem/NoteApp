package com.example.appnote.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo
    var title:String,
    @ColumnInfo
    var description:String,
    @ColumnInfo
    var time:String) {
    @PrimaryKey
    var id:Int?=null
}