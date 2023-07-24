package com.example.note.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.note.data.Repository.NoteRepository
import com.example.note.data.db.NoteDatabase
import com.example.note.databinding.ActivityMainBinding
import com.example.note.ui.viewmodel.NoteViewModel
import com.example.note.ui.viewmodel.factory.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()
    }

    private fun setViewModel() {
        val noteRepository = NoteRepository(
            NoteDatabase(applicationContext)
        )

        val viewModelProviderFactory =
            NoteViewModelFactory(
                application, noteRepository
            )

        noteViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        )[NoteViewModel::class.java]
    }
}