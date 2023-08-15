package com.example.note.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.note.data.repository.NoteRepository
import com.example.note.data.model.NoteModel
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val repository: NoteRepository) :
    AndroidViewModel(app) {

    fun addNote(note: NoteModel) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note: NoteModel) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: NoteModel) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return repository.getAllNotes()
    }
    fun searchNotes(query: String?) = repository.searchNotes(query)
}