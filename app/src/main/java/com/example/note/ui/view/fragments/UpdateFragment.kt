package com.example.note.ui.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.FragmentUpdateBinding
import com.example.note.ui.view.activities.MainActivity
import com.example.note.ui.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val argument : UpdateFragmentArgs by navArgs()
    private lateinit var currentNote : NoteModel
    private lateinit var  noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)

        binding.options.setOnClickListener { view ->
            val popupMenu = PopupMenu(binding.options.context, view)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {

                    R.id.menu_delete -> {
                        deleteNote()
                        true
                    }

                    else -> true
                }
            }
            popupMenu.inflate(R.menu.menu_main)

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        noteViewModel = (activity as MainActivity).noteViewModel
        currentNote = argument.note!!
        binding.editTitle.setText(currentNote.title)
        binding.editDescription.setText(currentNote.description)
        binding.btnEditNote.setOnClickListener{
            updateNote()
        }
    }

    private fun updateNote() {
        val noteTitle = binding.editTitle.text.toString().trim()
        val noteDesc = binding.editDescription.text.toString().trim()
        if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
            val note = NoteModel(currentNote.id, noteTitle, noteDesc)
            noteViewModel.updateNote(note)
            Snackbar.make(binding.root, "داده مورد نظر بروز شد", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("حذف نام")
            setMessage("ایا از حذف این پرونده مطمعن هستید ؟")
            setPositiveButton("بله") { _, _ ->
                noteViewModel.deleteNote(currentNote)
                findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }
            setNegativeButton("خیر", null)
        }.create().show()
    }

}