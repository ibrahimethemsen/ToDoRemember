package com.ibrahimethem.todoremember.domain.usecase

import com.ibrahimethem.todoremember.data.repository.TodoRepository
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember

class DeleteTodoUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(todoRemember: TodoRemember){
        todoRepository.deleteTodo(todoRemember)
    }
}