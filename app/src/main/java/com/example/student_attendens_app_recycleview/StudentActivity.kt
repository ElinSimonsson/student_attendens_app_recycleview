package com.example.student_attendens_app_recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


const val STUDENT_POSITION_KEY = "STUDENT_POSITION_KEY"
const val POSITION_NOT_SET = -1

class StudentActivity : AppCompatActivity() {

    lateinit var nameEditText: EditText
    lateinit var classEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        nameEditText = findViewById(R.id.editNameView)
        classEditText = findViewById(R.id.editClassView)

        val saveButton = findViewById<Button>(R.id.saveButton)
        val studentPosition = intent.getIntExtra(STUDENT_POSITION_KEY, POSITION_NOT_SET)
        if(studentPosition != POSITION_NOT_SET) {
            displayStudent(studentPosition)
            saveButton.setOnClickListener {
                editStudent(studentPosition)
            }
        } else {
            saveButton.setOnClickListener {
                addStudent()
            }
        }

    }

    fun editStudent (position: Int) {
        DataManager.students[position].name = nameEditText.text.toString()
        DataManager.students[position].className = classEditText.text.toString()
        finish()
    }

    fun displayStudent(position: Int) {
        val student = DataManager.students[position]

        nameEditText.setText(student.name)
        classEditText.setText((student.className))
    }

    fun addStudent () {
        val name = nameEditText.text.toString()
        val className = classEditText.text.toString()

        if (name != "" && className != "") {
            val student = Student(name, className)
            DataManager.students.add(student)
            finish()
        }
    }
}