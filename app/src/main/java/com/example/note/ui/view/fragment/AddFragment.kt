package com.example.note.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.note.R
import com.example.note.data.model.NoteModel
import com.example.note.databinding.FragmentAddBinding
import com.example.note.ui.view.activity.MainActivity
import com.example.note.ui.viewmodel.NoteViewModel
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
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

            setNoteSave()
        }
    }

    private fun setNoteSave() {
        noteViewModel = (activity as MainActivity).noteViewModel
        val noteTitle = binding.addTitle.text.toString().trim()
        val noteDesc = binding.addDescription.text.toString().trim()

        if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
            //Add Note
            val note = NoteModel(0, noteTitle, noteDesc)
            noteViewModel.addNote(note)

            //Show Message
            val snackbar = Snackbar.make(binding.root, "اطلاعات با موفقیت به طور کامل اضافه شد", Snackbar.LENGTH_LONG)
            snackbar.duration = 3000
            snackbar.setTextColor(resources.getColor(R.color.white))
            snackbar.setBackgroundTint(resources.getColor(R.color.success_color))
            snackbar.view.layoutDirection = View.LAYOUT_DIRECTION_RTL
            snackbar.show()

            //Action to HomeFragment
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
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