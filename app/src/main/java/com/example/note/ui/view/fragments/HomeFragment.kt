package com.example.note.ui.view.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.FragmentHomeBinding
import com.example.note.ui.view.activities.MainActivity
import com.example.note.ui.view.adapter.NoteAdapter
import com.example.note.ui.viewmodel.NoteViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        noteAdapter = NoteAdapter()
        binding.recyclerview.apply {
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner) {note->
                noteAdapter.differ.submitList(note)
            }
        }

    }

}