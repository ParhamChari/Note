package com.example.note.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    lateinit var binding: FragmentUpdateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)

        binding.btnEditNote.setOnClickListener{
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }

        return binding.root
    }

}