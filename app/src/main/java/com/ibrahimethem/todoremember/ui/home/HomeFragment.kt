package com.ibrahimethem.todoremember.ui.home


import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ibrahimethem.todoremember.R
import com.ibrahimethem.todoremember.base.BaseFragment
import com.ibrahimethem.todoremember.databinding.FragmentHomeBinding
import com.ibrahimethem.todoremember.util.Consts.LANGUAGE
import com.ibrahimethem.todoremember.util.Consts.LAT
import com.ibrahimethem.todoremember.util.Consts.LON
import com.ibrahimethem.todoremember.util.Consts.WEATHER_API_KEY
import com.ibrahimethem.todoremember.util.extensions.downloadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLocationWeather(LAT, LON, WEATHER_API_KEY, LANGUAGE)
        viewModel.getQuote()
        viewModel.getDate()
    }

    override fun onViewFinished() {
        observe()
        todoAdd()

    }


    fun observe() {
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
    fun todoAdd(){
        binding.addTodoRemember.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddTodoBottomSheetFragment())
        }
    }
}