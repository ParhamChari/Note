package com.example.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.note.databinding.FragmentAddBinding
import com.example.note.databinding.FragmentHomeBinding

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)

        binding.btnAddNote.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }

        return binding.root
    }

}