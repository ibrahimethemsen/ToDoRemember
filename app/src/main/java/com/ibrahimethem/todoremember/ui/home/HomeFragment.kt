package com.ibrahimethem.todoremember.ui.home




import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding

import com.ibrahimethem.todoremember.R
import com.ibrahimethem.todoremember.databinding.FragmentHomeBinding
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import com.ibrahimethem.todoremember.util.Consts.LANGUAGE
import com.ibrahimethem.todoremember.util.Consts.LAT
import com.ibrahimethem.todoremember.util.Consts.LON
import com.ibrahimethem.todoremember.util.Consts.WEATHER_API_KEY
import com.ibrahimethem.todoremember.util.extensions.downloadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding : FragmentHomeBinding by viewBinding()

    private val viewModel: HomeViewModel by viewModels()

    private var todoRecyclerAdapter : TodoRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLocationWeather(LAT, LON, WEATHER_API_KEY, LANGUAGE)
        viewModel.getQuote()
        viewModel.getDate().also {
            setAdapter(it)
        }
        observe()
        todoAdd()
    }

    private fun observe() {
        viewModel.weatherResponse.observe(viewLifecycleOwner) {
            binding.weatherTemp.text = getString(R.string.celcius_value, it.temp.toString())
            binding.weatherDescription.text = it.description
            //http adresden indirmeye çalıştığımız için network config yaptık
            binding.weatherImage.downloadImage(it.weather)
        }
        viewModel.quoteResponse.observe(viewLifecycleOwner) { result ->
            binding.apply {
                quoteContent.text = result?.content
                quoteAuthor.text = result?.author
            }
        }
        viewModel.homeDate.observe(viewLifecycleOwner) { date ->
            binding.apply {
                dateDay.text = date
            }
        }
    }
    private fun todoAdd(){
        binding.addTodoRemember.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddTodoBottomSheetFragment())
        }
    }
    private fun setAdapter(date : String){
        todoRecyclerAdapter = TodoRecyclerAdapter(
            ::selectedTodo,::checkTodo
        )
        binding.todoRecycler.adapter = todoRecyclerAdapter
        lifecycle.coroutineScope.launch{
            viewModel.getAllTodo(date).collect{
                todoRecyclerAdapter?.let { recyclerAdapter ->
                    recyclerAdapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        todoRecyclerAdapter = null
        super.onDestroyView()
    }

    private fun selectedTodo(id: String?){
        val action = id?.let {
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
        }
        action?.let {
            requireView().findNavController().navigate(it)
        }
    }

    private fun checkTodo(todoRemember: TodoRemember){
        viewModel.updateTodoCheck(todoRemember)
    }
}