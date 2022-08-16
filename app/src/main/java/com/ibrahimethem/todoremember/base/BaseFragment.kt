package com.ibrahimethem.todoremember.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding,VM : ViewModel>(
    private val bindingInflater : (layoutInflater : LayoutInflater) -> VB
) : Fragment() {
    //viewbinding
    private var _binding : VB? = null
    protected val binding get() = _binding as VB

    //viewmodel
    protected abstract val viewModel : VM
    protected abstract fun onViewFinished()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater)
        if (_binding == null){
            throw IllegalArgumentException("binding null")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewFinished()
    }

    override fun onDestroy() {
        super.onDestroy()
        //memory leak
        _binding = null
    }
}