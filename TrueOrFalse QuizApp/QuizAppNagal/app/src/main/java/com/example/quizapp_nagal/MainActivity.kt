package com.example.quizapp_nagal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizapp_nagal.databinding.ActivityMainBinding
import com.example.quizapp_nagal.datamodel.Question
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionList: ArrayList<Question>
    private var currentQuestion: Int = 0
    private var corrects: Int = 0
    private val inclist: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = arrayListOf<Question>(
            Question("1. The latest Android OS version is Android X", false),
            Question("2. MAD stands for Mobile Application Development.", true),
            Question("3. Java is the first language being used for Android Development.", true),
            Question("4. You could use Kotlin in Android Development.", true),
            Question("5. The company developers of Android Studio were Microsoft.", false),
            Question("6. Android Studio supports programming language C++.", true),
            Question("7. JSON elements in android are only: boolean, null, array, and object.", false),
            Question("8. The file extension for a Kotlin files are: .kt, .kts", true),
            Question("9. In inserting a multi-line comment should be enclosed by /* -text here- */ .", true),
            Question("10. Kotlin uses the 'static' keyword.", false)
        )


        displayQuestion()
        binding.btnTrue.setOnClickListener{
            checkAnswer(true, questionList.get(currentQuestion).answer)
            binding.btnTrue.visibility = View.INVISIBLE
            binding.btnFalse.visibility = View.INVISIBLE
        }
        binding.btnFalse.setOnClickListener{
            checkAnswer(false, questionList.get(currentQuestion).answer)
            binding.btnTrue.visibility = View.INVISIBLE
            binding.btnFalse.visibility = View.INVISIBLE
        }
        binding.btnNext.setOnClickListener{
            if (currentQuestion < questionList.size -1){
            currentQuestion++
            displayQuestion()
                binding.btnTrue.visibility = View.VISIBLE
                binding.btnFalse.visibility = View.VISIBLE
            }
            else if (currentQuestion < questionList.size){
                results()
            }

        }

        binding.restart.setOnClickListener{
            inclist.clear()
            binding.Incorrects.text = null
            corrects = 0
            currentQuestion = 0
            displayQuestion()
            binding.btnNext.visibility = View.VISIBLE
            binding.btnTrue.visibility = View.VISIBLE
            binding.btnFalse.visibility = View.VISIBLE
            binding.restart.visibility = View.INVISIBLE
            binding.Incorrects.visibility = View.INVISIBLE

        }
    }

    private fun displayQuestion() {
        binding.tvQuestion.text = questionList.get(currentQuestion).questionText
    }

    private fun checkAnswer(userAnswer: Boolean, correctAnswer: Boolean) {

        if(userAnswer == correctAnswer)
        {
            corrects++
        }
        else
        {
            val wrongs = questionList.get(currentQuestion).questionText
            inclist.add(0, "$wrongs INCORRECT\n")
            displayIncorrects()
        }

    }

    private fun displayIncorrects() {
        binding.Incorrects.text = inclist.toString()
    }
    private fun results(){
        var size = questionList.size
        binding.tvQuestion.text = "You got a total score of: $corrects/$size "
        binding.Incorrects.visibility = View.VISIBLE
        binding.btnNext.visibility = View.INVISIBLE
        binding.btnTrue.visibility = View.INVISIBLE
        binding.btnFalse.visibility = View.INVISIBLE
        binding.restart.visibility = View.VISIBLE

    }

}