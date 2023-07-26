package com.example.note.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val description: String
) :Parcelable
