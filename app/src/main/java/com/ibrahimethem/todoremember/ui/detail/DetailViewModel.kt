package com.ibrahimethem.todoremember.ui.detail

import androidx.lifecycle.*
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import com.ibrahimethem.todoremember.domain.usecase.GetTodoUseCase
import com.ibrahimethem.todoremember.domain.usecase.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getTodo : GetTodoUseCase,
    private val updateTodo : UpdateTodoUseCase
    ) : ViewModel() {
    fun getTodoRemember(id : Int) : LiveData<TodoRemember>{
        return getTodo.invoke(id).asLiveData()
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

    //todoRemember olusturacak ve
    fun addNewTodo(todoRemember: TodoRemember,title : String,description : String?,check : Boolean?){
        val newTodo = todoRemember.copy(title = title, description = description, check = check)
        println("newTodo $newTodo")
        updatetodo(newTodo)
    }

    private fun updatetodo(todoRemember : TodoRemember){
        viewModelScope.launch {
            updateTodo.invoke(todoRemember)
            println("todo $todoRemember")
        }
    }
}