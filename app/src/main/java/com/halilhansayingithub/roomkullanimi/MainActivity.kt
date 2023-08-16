package com.halilhansayingithub.roomkullanimi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.halilhansayingithub.roomkullanimi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db : Database
    private lateinit var tdao : TeamsDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Database.databaseAccess(this)!!
        tdao = db.getTeamsDao()
        binding.textViewMain.text = resources.getString(R.string.main_text)
        binding.startTestButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,QuizActivity::class.java))
        }

    }
}