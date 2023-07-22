package com.example.note.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao
}