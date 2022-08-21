package com.ibrahimethem.todoremember.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ibrahimethem.todoremember.R

import com.ibrahimethem.todoremember.databinding.FragmentDetailBinding
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    //TODO -> calismiyor has null arguments
    //private val args: DetailFragmentArgs by navArgs()

    //primitive type doğrudan lateinit yapamıyoruz
    private var selectedId = -1

    private val binding : FragmentDetailBinding by viewBinding()
    private val viewModel : DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            selectedId = DetailFragmentArgs.fromBundle(it).selectedTodo.toInt()
        }
        viewModel.getTodoRemember(selectedId).observe(viewLifecycleOwner){selectedTodoRemember ->
            selectedTodoRemember?.let { todoCurrent ->
                bind(todoCurrent)
                binding.detailSave.setOnClickListener {
                    updateTodo(todoCurrent)
                    val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
                    it.findNavController().navigate(action)
                }
            }
        }
        binding.detailBackBtn.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
            it.findNavController().navigate(action)
        }
    }


    private fun bind(todo : TodoRemember){
        binding.apply {
            detailTitleTv.setText(todo.title)
            todo.check?.let {
                detailCheck.isChecked = it
            }
            detailDescription.setText(todo.description)
        }
    }
    private fun isEntryValid() : Boolean{
        return viewModel.isEntryValid(
            binding.detailTitleTv.text.toString()
        )
    }

    private fun updateTodo(todo: TodoRemember){
        if(isEntryValid()){
            viewModel.addNewTodo(
                todoRemember = todo,
                title = binding.detailTitleTv.text.toString(),
                description = binding.detailDescription.text.toString(),
                check = binding.detailCheck.isChecked
            )
        }
    }


}