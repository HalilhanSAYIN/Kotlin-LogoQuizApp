package com.halilhansayingithub.roomkullanimi

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.halilhansayingithub.roomkullanimi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val correctCounter = intent.getIntExtra("correctCounter", 0)

        binding.textViewResult.text =
            "$correctCounter ${resources.getString(R.string.correct_count)}   ${5 - correctCounter} ${
                resources.getString(R.string.false_count)
            }"

        binding.textViewPercentResult.text =
            "%${(correctCounter * 100) / 5} ${resources.getString(R.string.success)}"

        binding.buttonRetry.setOnClickListener {
            startActivity(Intent(this@ResultActivity, QuizActivity::class.java))
            finish()
        }
        val resultTextView = findViewById<TextView>(R.id.textViewResultMessage)
        when (correctCounter) {
            0 -> resultTextView.text = resources.getString(R.string.zero_true)
            1 -> resultTextView.text = resources.getString(R.string.one_true)
            2 -> resultTextView.text = resources.getString(R.string.two_true)
            3 -> resultTextView.text = resources.getString(R.string.three_true)
            4 -> resultTextView.text = resources.getString(R.string.four_true)
            5 -> resultTextView.text = resources.getString(R.string.five_true)
        }
    }
}