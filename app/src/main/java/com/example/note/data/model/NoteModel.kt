package com.example.note.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(
    private val title: String,
    private val description: String,
    @PrimaryKey(autoGenerate = true) private val id: Int? = null
)
