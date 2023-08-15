package com.example.note.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.FragmentUpdateBinding
import com.example.note.ui.view.activity.MainActivity
import com.example.note.ui.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
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
            //Update Note
            val note = NoteModel(currentNote.id, noteTitle, noteDesc)
            noteViewModel.updateNote(note)

            //Show Message
            val snackbar = Snackbar.make(binding.root, "داده مورد نظر بروز شد", Snackbar.LENGTH_LONG)
            snackbar.duration = 3000
            snackbar.setTextColor(resources.getColor(R.color.white))
            snackbar.setBackgroundTint(resources.getColor(R.color.success_color))
            snackbar.view.layoutDirection = View.LAYOUT_DIRECTION_RTL
            snackbar.show()

            //Action to HomeFragment
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
        else {
            //Show Error
            val snackbar = Snackbar.make(binding.root, "لطفا اطلاعات را وارد کنید", Snackbar.LENGTH_LONG)
            snackbar.duration = 1500
            snackbar.setTextColor(resources.getColor(R.color.white))
            snackbar.setBackgroundTint(resources.getColor(R.color.error_color))
            snackbar.view.layoutDirection = View.LAYOUT_DIRECTION_RTL
            snackbar.show()
        }

    }

}