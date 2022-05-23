package com.example.classwork

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val outputField = findViewById<EditText>(R.id.fsa_output)
        val inputField = findViewById<EditText>(R.id.fsa_input)
        val subBtn = findViewById<Button>(R.id.fsa_sumBtn)
        val test = intent.getIntExtra("test", 0)
        outputField.setText(test.toString())
        subBtn.setOnClickListener {
            val intent = Intent(MainActivity2@this, MainActivity::class.java)
            intent.putExtra("test", if (inputField.text.isEmpty()) 0 else inputField.text.toString().toInt())
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
        inputField.requestFocus()
    }
}
