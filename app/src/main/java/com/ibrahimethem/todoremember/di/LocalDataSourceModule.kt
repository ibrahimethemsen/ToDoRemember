package com.ibrahimethem.todoremember.di

import com.ibrahimethem.todoremember.data.database.TodoDao
import com.ibrahimethem.todoremember.data.localdatasource.TodoLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @[Provides Singleton]
    fun provideTodoLocalDataSource(todo : TodoDao) : TodoLocalDataSource = TodoLocalDataSource(todo)
}