package com.example.startquiz

data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)
