package com.example.note.ui.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.R
import com.example.note.databinding.FragmentHomeBinding
import com.example.note.ui.view.activity.MainActivity
import com.example.note.ui.adapter.NoteAdapter
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val edtSearch = binding.search
        val clear = binding.clearIcon

        clear.setOnClickListener {
            edtSearch.setText("")
        }

        edtSearch.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                searchNote(s.toString())

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchNote(s.toString())

                when {
                    edtSearch.text.isEmpty() -> clear.visibility = View.INVISIBLE

                    else -> clear.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        noteAdapter = NoteAdapter()

        //Action to AddFragment
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner) {
                noteAdapter.differ.submitList(it)
            }
        }

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

    }


    private fun searchNote(query: String?) {
        val searchQuery = "%$query%"
        noteViewModel.searchNotes(searchQuery).observe(this) { list->
            noteAdapter.differ.submitList(list)
        }
    }

}