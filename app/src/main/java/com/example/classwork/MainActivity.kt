package com.example.classwork

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity() : AppCompatActivity() {

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val textView = findViewById<TextView>(R.id.input)
        val test = intent.getIntExtra("test", 0)
        textView.text = if (test == 0) "" else test.toString()
        super.onRestoreInstanceState(savedInstanceState)
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numericPad = arrayListOf<Button>()
        findViewById<ConstraintLayout>(R.id.MainActivity)
        val inputField = findViewById<TextView>(R.id.input)
        var inputBuffer: Int? = null
        var flag: Int? = null
        val test = intent.getIntExtra("test", 0)
        inputField.text = if (test == 0) "" else test.toString()
        val outputField = findViewById<TextView>(R.id.output)

        for (i in 0..9)
        {
            numericPad.add(findViewById(resources.getIdentifier("number_$i","id",this.packageName)))
        }

        for (button in numericPad) {
            button.setOnClickListener {
                randomiseBGColor(button)
                (inputField.text.toString() + button.text).also { inputField.text = it }
            }
        }

        val sumBtn = findViewById<Button>(R.id.sumBtn)
        sumBtn.setOnClickListener {
            if (inputField.text.isEmpty()) return@setOnClickListener
            if (inputBuffer == null)
            {
                inputBuffer =  Integer.parseInt(inputField.text.toString())
                inputField.text = ""
                flag = 0
            }
            else
            {
                val temp = Integer.parseInt(inputField.text.toString())
                "${inputBuffer!! + temp}".also { outputField.text = it }
                inputBuffer = inputBuffer!! + temp
                inputField.text = ""

            }
        }
        val subBtn = findViewById<Button>(R.id.subBtn)
        subBtn.setOnClickListener {
            if (inputField.text.isEmpty()) return@setOnClickListener
            if (inputBuffer == null)
            {
                inputBuffer =  Integer.parseInt(inputField.text.toString())
                inputField.text = ""
                flag = 1
            }
            else
            {
                val temp = Integer.parseInt(inputField.text.toString())
                "${inputBuffer!! - temp}".also { outputField.text = it }
                inputBuffer = inputBuffer!! - temp
                inputField.text = ""

            }
        }
        val mulBtn = findViewById<Button>(R.id.mulBtn)
        mulBtn.setOnClickListener {
            if (inputField.text.isEmpty()) return@setOnClickListener
            if (inputBuffer == null) {
                inputBuffer = Integer.parseInt(inputField.text.toString())
                inputField.text = ""
                flag = 2
            } else {
                val temp = Integer.parseInt(inputField.text.toString())
                "${inputBuffer!! * temp}".also { outputField.text = it }
                inputBuffer = inputBuffer!! * temp
                inputField.text = ""

            }
        }

        val divBtn = findViewById<Button>(R.id.divBtn)
        divBtn.setOnClickListener {
            if (inputField.text.isEmpty()) return@setOnClickListener
            if (inputBuffer == null)
            {
                inputBuffer =  Integer.parseInt(inputField.text.toString())
                inputField.text = ""
                flag = 3
            }
            else
            {
                val temp = Integer.parseInt(inputField.text.toString())
                "${inputBuffer!! / temp}".also { outputField.text = it }
                inputBuffer = inputBuffer!! / temp
                inputField.text = ""

            }
        }

        val calcBtn = findViewById<Button>(R.id.calculate)
        calcBtn.setOnClickListener {
            if (inputField.text.isEmpty()) return@setOnClickListener
            if (inputBuffer == null) return@setOnClickListener
            else
            if (flag != null)
            {
                when (flag) {
                    0 -> {
                        val temp = Integer.parseInt(inputField.text.toString())
                        "${inputBuffer!! + temp}".also { outputField.text = it }
                        inputBuffer = inputBuffer!! + temp
                        inputField.text = ""
                        flag = null
                    }
                    1 -> {
                        val temp = Integer.parseInt(inputField.text.toString())
                        "${inputBuffer!! - temp}".also { outputField.text = it }
                        inputBuffer = inputBuffer!! - temp
                        inputField.text = ""
                        flag = null
                    }
                    2 -> {
                        val temp = Integer.parseInt(inputField.text.toString())
                        "${inputBuffer!! * temp}".also { outputField.text = it }
                        inputBuffer = inputBuffer!! * temp
                        inputField.text = ""
                        flag = null
                    }
                    3 -> {
                        val temp = Integer.parseInt(inputField.text.toString())
                        "${inputBuffer!! / temp}".also { outputField.text = it }
                        inputBuffer = inputBuffer!! / temp
                        inputField.text = ""
                        flag = null
                    }
                }
                inputBuffer = null

            }
        }

        val testBtn = findViewById<Button>(R.id.testBtn)
        testBtn.setOnClickListener {
            // open FullScreenActivity
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("test", if (outputField.text.isBlank()) 0 else outputField.text.toString().toInt())
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

    }

    override fun onDestroy() {
        Toast.makeText(this,"KILLED",Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}

fun randomiseBGColor(button: Button) {
    val color = Color.rgb(Random.nextInt(0,255),Random.nextInt(0,255),Random.nextInt(0,255))
    button.setBackgroundColor(color)

}

fun divPass(str: String): String {
    val tokens = str.split('/')
    if (tokens.size  <= 1) return str
    return str
}