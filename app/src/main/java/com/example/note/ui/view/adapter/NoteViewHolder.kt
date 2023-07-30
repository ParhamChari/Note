package com.example.note.ui.view.adapter

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note.data.model.NoteModel
import com.example.note.databinding.NoteItemBinding
import com.example.note.ui.view.fragments.HomeFragmentDirections

class NoteViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root){

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