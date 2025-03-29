package com.example.startquiz

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.appcompat.widget.Toolbar


class ShowQuestions : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var nextButton: Button
    lateinit var toolbar: Toolbar
    private var correctSound: MediaPlayer? = null
    private var wrongSound: MediaPlayer? = null

    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questionList: List<Quiz>
    private lateinit var db: SignupDatabaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_questions)

        questionText = findViewById(R.id.txtQuestion)
        radioGroup = findViewById(R.id.radioGroupOptions)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        nextButton = findViewById(R.id.button1)

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, dashboard::class.java)
            startActivity(intent)
            finish()
        }


        correctSound = MediaPlayer.create(this, R.raw.correct)
        wrongSound = MediaPlayer.create(this, R.raw.wrong)


        db = SignupDatabaseClass.getDatabase(this)

        lifecycleScope.launch(Dispatchers.IO) {
            val quizDao = db.quizDao()
            questionList = quizDao.getAllQuestions()


            if (questionList.isEmpty()) {
                val defaultQuestions = listOf(

                    Quiz(0, "Which programming language is primarily used for Android development?", "Swift", "Java", "C++", "Python", "Java"),
                    Quiz(0,"What is the latest officially supported language for Android development?","C++","Kotlin","JavaScript","Ruby","Kotlin"),
                    Quiz(0,"Which component of Android is responsible for managing the UI of an app?","Activity","Service","Content Provider","View","Activity"),
                    Quiz(0,"Which file contains essential app metadata like permissions and activities?","AndroidManifest.xml","MainActivity.java","MainActivity.kt","MainActivity.xml","AndroidManifest.xml"),
                    Quiz(0,"What is an Intent in Android?"," A type of database","A messaging object for communication"," A UI component","A service that runs in the background","A messaging object for communication"),
                    Quiz(0,"What is the parent class of all Android UI components?","View","Activity","Fragment","ViewGroup","View"),
                    Quiz(0,"Which UI component is best for displaying a large dataset efficiently?","ListView","RecyclerView","GridView","ScrollView","RecyclerView"),
                    Quiz(0,"Which component is used to perform background operations without UI?","Service","Activity","Broadcast Receiver","ContentProvider","Service"),
                    Quiz(0,"What does the onCreate() method in an Activity do?","Initializes the activity","Handles button clicks"," Closes the app"," Displays a toast message","Initializes the activity"),
                    Quiz(0,"What is the main purpose of SharedPreferences?","Store large files","Store key-value pairs persistently","Display notifications","Manage network requests","Store key-value pairs persistently"),
                    Quiz(0,"What is the role of a BroadcastReceiver?","To display notiflications","To handle backgroung tasks","To manage network requests","To respond to system-wide events","To respond to system-wide events"),
                    Quiz(0,"Which of the following is NOT a valid Android storage option?","Internal Storage","External Storage","Cloud  Storage","RAM Storage","RAM  Storage"),
                    Quiz(0,"Which component is responsible for storing structured data in Android?","SQLite","SharedPreferences","Firebase","Intent","SQLite"),
                )

                quizDao.insertQuestions(defaultQuestions)


                questionList = quizDao.getAllQuestions()
            }

            nextButton.setOnClickListener {
                checkAnswer()
            }


            Log.d("Quiz", "Number of questions: ${questionList.size}")
            questionList.forEach { question ->
                Log.d("Quiz", "Question: ${question.question}")
            }

            runOnUiThread {
                if (questionList.isNotEmpty()) {
                    displayQuestion()
                } else {
                    Toast.makeText(this@ShowQuestions, "No questions available", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    private fun displayQuestion() {
        Log.d("Quiz", "Displaying question at index: $currentQuestionIndex")
        if (currentQuestionIndex < questionList.size) {
            val question = questionList[currentQuestionIndex]

            runOnUiThread {
                questionText.text = question.question
                option1.text = question.option1
                option2.text = question.option2
                option3.text = question.option3
                option4.text = question.option4
                radioGroup.clearCheck()
            }
        } else {

            showScore()
        }
    }

    private fun checkAnswer() {
        val selectedOptionId = radioGroup.checkedRadioButtonId
        if (selectedOptionId != -1) {
            val selectedAnswer = findViewById<RadioButton>(selectedOptionId).text.toString()
            if (selectedAnswer == questionList[currentQuestionIndex].answer) {
                score++
                correctSound?.start()
            } else {
                wrongSound?.start()
            }
            currentQuestionIndex++
            if (currentQuestionIndex < questionList.size) {
                displayQuestion()
            } else {
                showScore()
            }
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showScore() {
        AlertDialog.Builder(this)
            .setTitle("Quiz Completed")
            .setMessage("Your score is $score/${questionList.size}")
            .setPositiveButton("OK") { _, _ -> finish() }
            .show()
    }
}
