package com.halilhansayingithub.roomkullanimi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.halilhansayingithub.roomkullanimi.databinding.ActivityQuizBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questions: ArrayList<Logos>
    private lateinit var wrongAnswers: ArrayList<Logos>
    private lateinit var correctQuestion: Logos
    private lateinit var allOptions: HashSet<Logos>
    private lateinit var tdao: TeamsDao

    private var questionCounter = 0
    private var correctCounter = 0
    private var wrongCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textViewCorrect.text = resources.getString(R.string.correct_count)
        binding.textViewWrong.text =   resources.getString(R.string.false_count)

        val db = Database.INSTANCE
        if (db != null) {
            tdao = db.getTeamsDao()
        }

        CoroutineScope(Dispatchers.Main).launch {
            questions = loadQuestions()
            loadQuestion()
        }

        binding.buttonA.setOnClickListener {
            correctControl(binding.buttonA)
            questionCounterControl()
        }
        binding.buttonB.setOnClickListener {
            correctControl(binding.buttonB)
            questionCounterControl()
        }
        binding.buttonC.setOnClickListener {
            correctControl(binding.buttonC)
            questionCounterControl()
        }
        binding.buttonD.setOnClickListener {
            correctControl(binding.buttonD)
            questionCounterControl()
        }
    }

    suspend fun loadQuestions(): ArrayList<Logos> {
        return ArrayList(tdao.loadQuestion())
    }

    suspend fun loadQuestion() {
        binding.textViewQuestion.text = "${resources.getString(R.string.question_count)} : ${questionCounter +1}"
        correctQuestion = questions[questionCounter]

        binding.imageViewLogo.setImageResource(
            resources.getIdentifier(
                correctQuestion.logo_image,
                "drawable",
                packageName
            )
        )

        wrongAnswers = ArrayList(tdao.getThreeRandomWrongLogos(correctQuestion.logo_id))

        allOptions = HashSet()
        allOptions.add(correctQuestion)
        allOptions.addAll(wrongAnswers)

        binding.buttonA.text = allOptions.elementAt(0).logo_name
        binding.buttonB.text = allOptions.elementAt(1).logo_name
        binding.buttonC.text = allOptions.elementAt(2).logo_name
        binding.buttonD.text = allOptions.elementAt(3).logo_name
    }

    fun questionCounterControl() {
        questionCounter++

        if (questionCounter != 5) {
            CoroutineScope(Dispatchers.Main).launch {
                loadQuestion()
            }
        } else {
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("correctCounter",correctCounter)
            startActivity(intent)
            finish()
        }
    }

    fun correctControl(button: Button) {
        val buttonText = button.text.toString()
        val correctAnswer = correctQuestion.logo_name

        if (buttonText == correctAnswer) {
            correctCounter++
        } else {
            wrongCounter++
        }

        binding.textViewCorrect.text = "${resources.getString(R.string.correct_count)} : $correctCounter"
        binding.textViewWrong.text = "${resources.getString(R.string.false_count)} : $wrongCounter"
    }
}



