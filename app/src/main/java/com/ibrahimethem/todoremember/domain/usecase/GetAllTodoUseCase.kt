package com.ibrahimethem.todoremember.domain.usecase

import com.ibrahimethem.todoremember.data.repository.TodoRepository
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import kotlinx.coroutines.flow.Flow

class GetAllTodoUseCase(
    private val todoRepository: TodoRepository
) {
    operator fun invoke(date : String) : Flow<List<TodoRemember>>{
        return todoRepository.getTodoAll(date)
    }
}