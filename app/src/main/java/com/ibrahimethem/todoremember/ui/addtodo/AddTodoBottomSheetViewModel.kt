package com.ibrahimethem.todoremember.ui.addtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimethem.todoremember.local.todo.TodoDao
import com.ibrahimethem.todoremember.model.todo.TodoRemember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTodoBottomSheetViewModel @Inject constructor(private val todoDao: TodoDao) : ViewModel() {

    private fun insertTodoRemember(todoRemember: TodoRemember) {
        viewModelScope.launch {
            todoDao.insertTodo(todoRemember)
        }
    }

    private fun getTodoRemember(
        date: String,
        title: String,
        check: Boolean? = false,
        description: String? = "",
        value: String? = "",
    ): TodoRemember {
        return TodoRemember(
            date = date,
            check = check,
            title = title,
            description = description,
            value = value
        )
    }

    fun addTodoRemember(
        date: String,
        title: String,
        check: Boolean? = false,
        description: String? = "",
        value: String? = ""
    ) {
        val todoRemember = getTodoRemember(
            date = date,
            check = check,
            title = title,
            description = description,
            value = value
        )
        insertTodoRemember(todoRemember)
    }

    fun getDate(): String {
        val formatter = SimpleDateFormat("dd-MM-EEE", Locale("tr"))
        val calendar = Calendar.getInstance()
        return formatter.format(calendar.time.time)
    }
}