package com.example.findhim

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.databinding.ActivityMainBinding
import com.example.findhim.databinding.FinalLayoutBinding
import com.example.findhim.persistency.Game

class FinalActivity : AppCompatActivity() {
    lateinit var binding : FinalLayoutBinding



    private lateinit var nickname: String
    private lateinit var clicks: String
    private lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinalLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val game = bundle?.getParcelable<Game>("game")
        // Find the views and initialize the variables
        nickname = game?.nickname ?: ""
        clicks = game?.clicks ?: ""
        time = game?.gameTime ?: ""
        binding.attempts.append(clicks)
        binding.totaltime.append(time)

        // Set up the click listeners
        binding.playagainButton.setOnClickListener { playAgain() }
        binding.submitButton.setOnClickListener { sendEmail() }
        binding.exitButton.setOnClickListener { finishAll() }
    }

    private fun finishAll() {

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finishAffinity()


    }


    private fun sendEmail() {

        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val email = binding.email.text.toString()

        // Check if the email is valid
        if (!isValidEmail(email)) {
            binding.email.error = "Invalid email address"
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

    private fun playAgain() {
        val intent = Intent(applicationContext, StartActivity::class.java).apply {
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
