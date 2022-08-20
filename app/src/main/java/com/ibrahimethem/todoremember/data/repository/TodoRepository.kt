package com.ibrahimethem.todoremember.data.repository


import com.ibrahimethem.todoremember.data.localdatasource.TodoLocalDataSource
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val todoLocalDataSource: TodoLocalDataSource
) {
    suspend fun insertTodo(todoRemember: TodoRemember){
        todoLocalDataSource.insertTodo(todoRemember)
    }

    suspend fun updateTodo(todoRemember: TodoRemember){
        todoLocalDataSource.updateTodo(todoRemember)
    }

    suspend fun deleteTodo(todoRemember: TodoRemember){
        todoLocalDataSource.deleteTodo(todoRemember)
    }

    fun getTodo(id : Int) : Flow<TodoRemember> {
        return todoLocalDataSource.getTodo(id)
    }

    fun getTodoAll(date : String) : Flow<List<TodoRemember>> {
        return todoLocalDataSource.getTodoAll(date)
    }
}