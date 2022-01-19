package com.example.quizapp_nagal.datamodel

class Question (questionText: String, answer: Boolean){
    var questionText: String
    var answer: Boolean

    init {
        this.questionText = questionText
        this.answer = answer
    }

}