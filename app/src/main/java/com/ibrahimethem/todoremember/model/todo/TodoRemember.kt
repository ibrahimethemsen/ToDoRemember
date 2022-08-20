package com.ibrahimethem.todoremember.model.todo


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//db de bir entity-tablo
@Entity(tableName = "todolist")
data class TodoRemember(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "date")
    val date : String?,
    @ColumnInfo(name = "check")
    val check : Boolean? = false,
    @ColumnInfo(name = "title")
    val title : String?,
    @ColumnInfo(name = "decription")
    val description : String? = "",
    @ColumnInfo(name = "value")
    val value : String? = ""
)
