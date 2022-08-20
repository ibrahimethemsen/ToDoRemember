package com.ibrahimethem.todoremember.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibrahimethem.todoremember.databinding.FragmentDetailBinding
import com.ibrahimethem.todoremember.model.todo.TodoRemember
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    //TODO -> calismiyor has null arguments
    //private val args: DetailFragmentArgs by navArgs()

    //primitive type doğrudan lateinit yapamıyoruz
    var selectedId = -1

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            selectedId = DetailFragmentArgs.fromBundle(it).selectedTodo.toInt()
        }
        viewModel.getTodoRemember(selectedId).observe(viewLifecycleOwner){selectedTodoRemember ->
            selectedTodoRemember?.let {
                bind(it)
            }
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
    private fun addNewTodo(){
        if (isEntryValid()){
            viewModel.addNewTodo()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //memory leak
        _binding = null
    }
}