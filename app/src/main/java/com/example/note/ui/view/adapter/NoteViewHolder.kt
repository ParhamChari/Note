package com.example.note.ui.view.adapter


import android.app.AlertDialog
import android.util.Log
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.NoteItemBinding
import com.example.note.ui.view.activities.MainActivity
import com.example.note.ui.view.fragments.HomeFragmentDirections
import com.example.note.ui.view.fragments.UpdateFragment
import com.example.note.ui.viewmodel.NoteViewModel


class NoteViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root) {
    private lateinit var noteModel: NoteModel
    fun bindViews(note: NoteModel) {
        binding.apply {
            title.text = note.title
            description.text = note.description

            cardView.setOnClickListener{view->
                val direction = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(note)
                view.findNavController().navigate(direction)
            }
        }
    }
}