package com.example.note.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.FragmentAddBinding
import com.example.note.ui.view.activities.MainActivity
import com.example.note.ui.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        binding.btnAddNote.setOnClickListener{
            val noteTitle = binding.addTitle.text.toString().trim()
            val noteDesc = binding.addDescription.text.toString().trim()
            setNoteSave()
        }
    }

    private fun setNoteSave() {
        noteViewModel = (activity as MainActivity).noteViewModel
        val noteTitle = binding.addTitle.text.toString().trim()
        val noteDesc = binding.addDescription.text.toString().trim()
        if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
            val note = NoteModel(0, noteTitle, noteDesc)
            noteViewModel.addNote(note)
            Snackbar.make(binding.root, "اطلاعات با موفقیت به طور کامل اضافه شد", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT).show()
        }
    }

}