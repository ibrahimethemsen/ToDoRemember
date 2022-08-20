package com.ibrahimethem.todoremember.data.localdatasource

import com.ibrahimethem.todoremember.data.database.TodoDao
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import kotlinx.coroutines.flow.Flow

class TodoLocalDataSource(
    private val todoDao: TodoDao
) {
    suspend fun insertTodo(todoRemember: TodoRemember){
        todoDao.insertTodo(todoRemember)
    }

    suspend fun updateTodo(todoRemember: TodoRemember){
        todoDao.updateTodo(todoRemember)
    }

    suspend fun deleteTodo(todoRemember: TodoRemember){
        todoDao.deleteTodo(todoRemember)
    }

    fun getTodo(id : Int) : Flow<TodoRemember> {
        return todoDao.getTodo(id)
    }

    fun getTodoAll(date : String) : Flow<List<TodoRemember>>{
        return todoDao.getTodoDate(date)
    }
}