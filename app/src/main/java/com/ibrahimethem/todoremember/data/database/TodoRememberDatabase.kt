package com.ibrahimethem.todoremember.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.ibrahimethem.todoremember.data.database.TodoDao
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember

@Database(
    entities = [TodoRemember::class],
    version = 1,
    exportSchema = false
)
abstract class TodoRememberDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}