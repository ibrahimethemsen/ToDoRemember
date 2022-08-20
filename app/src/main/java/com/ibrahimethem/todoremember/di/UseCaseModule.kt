package com.ibrahimethem.todoremember.di

import com.ibrahimethem.todoremember.data.repository.QuoteRepository
import com.ibrahimethem.todoremember.data.repository.TodoRepository
import com.ibrahimethem.todoremember.data.repository.WeatherRepository
import com.ibrahimethem.todoremember.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun getAllTodo(todoRepository: TodoRepository) = GetAllTodoUseCase(todoRepository)

    @Provides
    @ViewModelScoped
    fun insertTodo(todoRepository: TodoRepository) = InsertTodoUseCase(todoRepository)

    @Provides
    @ViewModelScoped
    fun updateTodo(todoRepository: TodoRepository) = UpdateTodoUseCase(todoRepository)

    @Provides
    @ViewModelScoped
    fun deleteTodo(todoRepository: TodoRepository) = DeleteTodoUseCase(todoRepository)

    @Provides
    @ViewModelScoped
    fun getTodo(todoRepository: TodoRepository) = GetTodoUseCase(todoRepository)

    @Provides
    @ViewModelScoped
    fun getLocationWeather(weatherRepository: WeatherRepository) = LocationWeatherUseCase(weatherRepository)

    @Provides
    @ViewModelScoped
    fun getRandomQuote(quoteRepository: QuoteRepository) = GetRandomQuoteUseCase(quoteRepository)
}