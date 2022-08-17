package com.ibrahimethem.todoremember.di

import android.content.Context
import androidx.room.Room
import com.ibrahimethem.todoremember.local.TodoRememberDatabase
import com.ibrahimethem.todoremember.local.todo.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {
    @[Provides Singleton]
    fun provideTodoDatabase(
        @ApplicationContext context: Context
    ): TodoRememberDatabase {
        return Room.databaseBuilder(
            context,
            TodoRememberDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideTodoDao(
        db : TodoRememberDatabase
    ) : TodoDao{
        return db.todoDao()
    }
}