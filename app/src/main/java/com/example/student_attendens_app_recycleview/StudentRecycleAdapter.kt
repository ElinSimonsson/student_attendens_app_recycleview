package com.example.studentattendensapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_attendens_app_recycleview.*

class StudentRecycleAdapter(val context : Context, val students : List<Student>) :
    RecyclerView.Adapter<StudentRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        holder.classView.text = student.className
        holder.nameView.text = student.name
        holder.presentButton.isChecked = student.present
        holder.studentPosition = position

    }

    override fun getItemCount() = students.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.findViewById<TextView>(R.id.nameTextView)
        val classView = itemView.findViewById<TextView>(R.id.classTextView)
        val presentButton = itemView.findViewById<CheckBox>(R.id.checkBox)
        var studentPosition = 0

        init {
            presentButton.setOnClickListener {
                DataManager.students[studentPosition].present = presentButton.isChecked

            }

            itemView.setOnClickListener {
                val intent = Intent(context, StudentActivity::class.java)
                intent.putExtra(STUDENT_POSITION_KEY, studentPosition)
                context.startActivity(intent)
            }

        }


    }
}

