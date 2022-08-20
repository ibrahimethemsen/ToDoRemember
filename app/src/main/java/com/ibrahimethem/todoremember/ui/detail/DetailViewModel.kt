package com.ibrahimethem.todoremember.ui.detail

import androidx.lifecycle.*
import com.ibrahimethem.todoremember.local.todo.TodoDao
import com.ibrahimethem.todoremember.model.todo.TodoRemember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val todoDao: TodoDao) : ViewModel() {
    fun getTodoRemember(id : Int) : LiveData<TodoRemember>{
        return todoDao.getTodo(id).asLiveData()
    }

    //veriler bos mu
    fun isEntryValid(
        title : String,
    ) : Boolean{
        if (title.isBlank()){
            return false
        }
        return true
    }

    fun addNewTodo(){

    }

    private fun insertTodo(todoRemember : TodoRemember){
        viewModelScope.launch {
            todoDao.insertTodo(todoRemember)
        }
    }
}