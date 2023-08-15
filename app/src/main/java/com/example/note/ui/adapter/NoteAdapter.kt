package com.example.note.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.note.data.model.NoteModel
import com.example.note.databinding.NoteItemBinding

class NoteAdapter : Adapter<NoteViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean =
            oldItem == newItem

    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.bindViews(currentItem)
    }

    override fun getItemCount(): Int = differ.currentList.size

}