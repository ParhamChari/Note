package com.example.note.ui.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.note.data.repository.NoteRepository
import com.example.note.ui.viewmodel.NoteViewModel

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(val app: Application, private val repository: NoteRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, repository) as T
    }
}