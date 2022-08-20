package com.ibrahimethem.todoremember.domain.usecase

import com.ibrahimethem.todoremember.data.repository.TodoRepository
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember
import kotlinx.coroutines.flow.Flow

class GetTodoUseCase(
    private val todoRepository: TodoRepository
) {
    operator fun invoke(id: Int): Flow<TodoRemember> {
        return todoRepository.getTodo(id)
    }
}