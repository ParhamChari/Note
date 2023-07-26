package com.example.note.ui.view.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note.data.model.NoteModel
import com.example.note.databinding.NoteItemBinding

class NoteViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root){

    fun bindViews(note: NoteModel) {
        binding.apply {
            title.text = note.title
            description.text = note.description
        }
    }
}