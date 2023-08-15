package com.example.note.ui.adapter


import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.NoteItemBinding
import com.example.note.ui.view.activity.MainActivity
import com.example.note.ui.view.fragment.HomeFragmentDirections
import com.example.note.ui.viewmodel.NoteViewModel


class NoteViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root) {
    private lateinit var noteViewModel: NoteViewModel
    private val context = binding.root.context

    fun bindViews(note: NoteModel) {
        binding.apply {
            title.text = note.title
            description.text = note.description

            options.setOnClickListener { view ->
                val popupMenu = PopupMenu(context, view)
                popupMenu.inflate(R.menu.menu_main)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {

                        R.id.menu_edit -> {
                            //Action to UpdateFragment
                            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(note)
                            view.findNavController().navigate(direction)
                            true
                        }

                        R.id.menu_delete -> {
                            //Delete CurrentNote
                            deleteCurrentNote(note)
                            true
                        }

                        else -> true
                    }
                }
                try {
                    val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                    fieldMPopup.isAccessible = true
                    val mPopup = fieldMPopup.get(popupMenu)
                    mPopup.javaClass
                        .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(mPopup)
                } catch (e: Exception) {
                    Log.e("Main", "Error showing menu icons.", e)
                } finally {
                    popupMenu.show()
                }

            }

        }
    }

    private fun deleteCurrentNote(note: NoteModel) {
        noteViewModel = (context as MainActivity).noteViewModel

        //Custom Title
        val title = TextView(context)
        title.text = "حذف پرونده"
        title.setTextColor(getColor(context, R.color.black))
        title.textSize = 22F
        title.setPadding(45, 35, 45, 35)
        title.layoutDirection = View.LAYOUT_DIRECTION_RTL

        AlertDialog.Builder(context).apply {
            setCustomTitle(title)
            setMessage("آیا از حذف این پرونده مطمعن هستید ؟")
            setPositiveButton("بله") { _, _ ->
                noteViewModel.deleteNote(note)
            }
            setNegativeButton("خیر", null)
            setCancelable(false)

        }.create().show()
    }
}