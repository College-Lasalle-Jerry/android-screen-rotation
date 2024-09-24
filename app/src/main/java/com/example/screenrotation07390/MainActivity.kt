package com.example.screenrotation07390

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // list of courses
    private var courses = mutableListOf<Course>()
    private var index: Int = 0

    private lateinit var editTextCourseNo: EditText
    private lateinit var editTextCourseName: EditText
    private lateinit var editTextCourseDuration: EditText

    // buttons
    private lateinit var buttonAddCourse: Button
    private lateinit var buttonPrevious: Button
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Link my xml to my code
        editTextCourseNo = findViewById(R.id.edittext_course_no)
        editTextCourseName = findViewById(R.id.edittext_course_name)
        editTextCourseDuration = findViewById(R.id.edittext_course_duration)
        // buttons
        buttonAddCourse = findViewById(R.id.button_add_course)
        buttonPrevious = findViewById(R.id.button_previous)
        buttonNext = findViewById(R.id.button_next)

        // manual method
        if (savedInstanceState != null) {
            editTextCourseNo.setText(savedInstanceState.getString("edittext_course_no"))
            editTextCourseName.setText(savedInstanceState.getString("edittext_course_name"))
            editTextCourseDuration.setText(savedInstanceState.getString("edittext_course_duration"))
            courses =
                savedInstanceState.getParcelableArrayList<Course>("courses_list") as MutableList<Course>
            index = savedInstanceState.getInt("index", 0)
        }

        buttonAddCourse.setOnClickListener {
            addCourse()
//            Log.d("MainActivity", "Course added: ${courses.size}")
        }

//        Log.d("MainActivity", "Course added: ${courses.size}")

        buttonNext.setOnClickListener {
            showNext()
        }

        buttonPrevious.setOnClickListener {
            showPrevious()
        }

    }

    private fun addCourse() {
        val courseNo = editTextCourseNo.text.toString()
        val courseName = editTextCourseName.text.toString()
        val courseDuration = editTextCourseDuration.text.toString().toInt()

        if(courseNo.isNotEmpty() && courseName.isNotEmpty() && courseDuration > 0){
            val newCourse = Course(courseNo, courseName, courseDuration)
            courses.add(newCourse)
            index = courses.size - 1
            updateUI()
            Toast.makeText(this, "Course added", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showNext() {
        if(index < courses.size - 1){
         index ++
         updateUI()
        }

    }

    private fun showPrevious() {
        // check if index is greater than 0
        if (index>0){
            index--
            updateUI()
        }

    }

    private fun updateUI() {
        if(courses.isNotEmpty() && index in courses.indices){
            val currentCourse = courses[index]
            editTextCourseNo.setText(currentCourse.courseNo)
            editTextCourseName.setText(currentCourse.courseName)
            editTextCourseDuration.setText(currentCourse.courseDuration.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) { // to save our data.
        super.onSaveInstanceState(outState)

        outState.putString("edittext_course_no", editTextCourseNo.text.toString())
        outState.putString("edittext_course_name", editTextCourseName.text.toString())
        outState.putString("edittext_course_duration", editTextCourseDuration.text.toString())
        outState.putParcelableArrayList("courses_list", ArrayList(courses))
        outState.putInt("index", index) // save the current index
    }

    // autmatic method.
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        editTextCourseNo.setText(savedInstanceState.getString("edittext_course_no"))
        editTextCourseName.setText(savedInstanceState.getString("edittext_course_name"))
        editTextCourseDuration.setText(savedInstanceState.getString("edittext_course_duration"))
        courses =
            savedInstanceState.getParcelableArrayList<Course>("courses_list") as MutableList<Course>
        index = savedInstanceState.getInt("index", 0)

    }
}