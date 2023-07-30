package com.example.note.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

}