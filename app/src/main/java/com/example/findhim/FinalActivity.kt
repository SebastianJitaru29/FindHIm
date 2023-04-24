package com.example.findhim

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.final_layout)

        // Find the three EditText views
        val editText1 = findViewById<EditText>(R.id.first_name)
        val editText2 = findViewById<EditText>(R.id.last_name)
        val editText3 = findViewById<EditText>(R.id.email)

        // Set a button click listener to do something with the user input
        val button = findViewById<Button>(R.id.submit_button)
        button.setOnClickListener {
            val input1 = editText1.text.toString()
            val input2 = editText2.text.toString()
            val input3 = editText3.text.toString()

            // Do something with the user input
            // For example, you can show a Toast message with the input values
            val message = "Input 1: $input1, Input 2: $input2, Input 3: $input3"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
