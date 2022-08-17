package com.ibrahimethem.todoremember.local.todo

import androidx.room.*
import com.ibrahimethem.todoremember.model.todo.TodoRemember
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todoRemember: TodoRemember)

    @Update
    suspend fun updateTodo(todoRemember: TodoRemember)

    @Delete
    suspend fun deleteTodo(todoRemember: TodoRemember)

    //selected todoremember-flow varken suspend kullanamiyoruz
    @Query("SELECT * FROM todolist WHERE id = :id")
    fun getTodo(id : Int) : Flow<TodoRemember>

    //date all todoremember
    @Query("SELECT * FROM todolist WHERE date = :date")
    fun getTodoDate(date : String) : Flow<List<TodoRemember>>
}