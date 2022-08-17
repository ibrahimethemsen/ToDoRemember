package com.ibrahimethem.todoremember.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.ibrahimethem.todoremember.local.todo.TodoDao
import com.ibrahimethem.todoremember.model.todo.TodoRemember

@Database(
    entities = [TodoRemember::class],
    version = 1,
    exportSchema = false
)
abstract class TodoRememberDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}