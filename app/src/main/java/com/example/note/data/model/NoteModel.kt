package com.example.note.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) private val id: Int? = null,
    private val title: String,
    private val description: String
)
