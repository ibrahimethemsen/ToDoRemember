package com.ibrahimethem.todoremember.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimethem.todoremember.databinding.ItemTodoCheckBinding
import com.ibrahimethem.todoremember.domain.model.todo.TodoRemember

class TodoRecyclerAdapter(
    private val selectedTodo: ((id: String) -> Unit)?,
    private val todoCheckListener : ( todo : TodoRemember) -> Unit
) :
    ListAdapter<TodoRemember, TodoRecyclerAdapter.TodoViewHolder>(
        DiffUtilCallBack
    ) {
    companion object {
        private val DiffUtilCallBack = object : DiffUtil.ItemCallback<TodoRemember>() {
            override fun areItemsTheSame(oldItem: TodoRemember, newItem: TodoRemember): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TodoRemember, newItem: TodoRemember): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    class TodoViewHolder(val todoBinding: ItemTodoCheckBinding) :
        RecyclerView.ViewHolder(todoBinding.root) {
        fun bind(todoRemember: TodoRemember) {
            todoBinding.apply {
                todoTitle.text = todoRemember.title
                todoRemember.check?.let {
                    todoCheck.isChecked = it
                    if (it){
                        todoBinding.lineCheck.visibility = View.VISIBLE
                    }else{
                        todoBinding.lineCheck.visibility = View.INVISIBLE
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoCheckBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = getItem(position)
        holder.todoBinding.todoTitle.setOnClickListener {
            selectedTodo?.invoke(current.id.toString())
        }
        holder.todoBinding.todoCheck.setOnClickListener {
            if (holder.todoBinding.todoCheck.isChecked) {
                val newTodo = current.copy(check = true)
                todoCheckListener.invoke(newTodo)
            } else {
                val newTodo = current.copy(check = false)
                todoCheckListener.invoke(newTodo)
            }
        }
        holder.bind(todoRemember = current)
    }
}