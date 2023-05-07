package com.example.findhim

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R
import org.w3c.dom.Text

class FinalActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var attemptsTextView: TextView
    private lateinit var totalTimeTextView: TextView
    private lateinit var playAgainButton: Button
    private lateinit var submitButton: Button
    private lateinit var exitButton: Button
    private lateinit var nickname: String
    private lateinit var clicks: String
    private lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.final_layout)

        // Find the views and initialize the variables
        firstNameEditText = findViewById(R.id.first_name)
        lastNameEditText = findViewById(R.id.last_name)
        emailEditText = findViewById(R.id.email)
        attemptsTextView = findViewById(R.id.attempts)
        totalTimeTextView = findViewById(R.id.totaltime)
        playAgainButton = findViewById(R.id.playagain_button)
        submitButton = findViewById(R.id.submit_button)
        exitButton = findViewById(R.id.exit_button)
        nickname = intent.getStringExtra("nickname") ?: ""
        clicks = intent.getStringExtra("clicks") ?: ""
        time = intent.getStringExtra("time") ?: ""
        attemptsTextView.append(clicks)
        totalTimeTextView.append(time)

        // Set up the click listeners
        playAgainButton.setOnClickListener { showNotImplementedToast() }
        submitButton.setOnClickListener { sendEmail() }
        exitButton.setOnClickListener { finishAllActivities() }
    }

    private fun showNotImplementedToast() {
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
    }

    private fun sendEmail() {
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val email = emailEditText.text.toString()

        // Check if the email is valid
        if (!isValidEmail(email)) {
            emailEditText.error = "Invalid email address"
            return
        }

        // Compose the email
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, "Logs FindHim")
            putExtra(
                Intent.EXTRA_TEXT,
                "Name: $firstName $lastName\nNickname: $nickname\nClicks: $clicks\nTime: $time"
            )
            flags = Intent.FLAG_ACTIVITY_NEW_TASK // Ensure the address is filled
        }

        // Check if an email client is installed
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No email client installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun finishAllActivities() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        // A simple email validation that checks if it contains @ and .
        return email.contains("@") && email.contains(".")
    }
}
