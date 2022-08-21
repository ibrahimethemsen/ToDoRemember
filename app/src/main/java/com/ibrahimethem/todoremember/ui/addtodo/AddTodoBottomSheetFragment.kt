package com.ibrahimethem.todoremember.ui.addtodo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibrahimethem.todoremember.databinding.FragmentAddTodoBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddTodoBottomSheetFragment @Inject constructor(): BottomSheetDialogFragment() {
    private var _binding: FragmentAddTodoBottomSheetBinding? = null
    val binding get() = _binding!!
    private val viewModel : AddTodoBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTodoBottomSheetBinding.inflate(layoutInflater, container, false)
        //klavye odaklanma sorunu -> https://stackoverflow.com/questions/39288879/bottom-sheet-fragment-comes-up-with-keyboard
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //direct focus
        binding.etAddTodo.requestFocus()
        binding.imageButton.setOnClickListener {
            addTodo()
            dismiss()
        }
    }

    private fun addTodo(){
        binding.apply {
            val title = etAddTodo.text.toString()
            val date = viewModel.getDate()
            viewModel.addTodoRemember(date = date, title = title)
        }
    }

}