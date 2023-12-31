package com.example.note.data.repository

import androidx.lifecycle.LiveData
import com.example.note.data.local.NoteDatabase
import com.example.note.data.model.NoteModel

class NoteRepository(private val noteDatabase: NoteDatabase) {

    suspend fun addNote(note: NoteModel) = noteDatabase.getNoteDao().addNote(note)
    suspend fun updateNote(note: NoteModel) = noteDatabase.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: NoteModel) = noteDatabase.getNoteDao().deleteNote(note)

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return noteDatabase.getNoteDao().getAllNotes()
    }
    fun searchNotes(query : String?) = noteDatabase.getNoteDao().searchNotes(query)
}