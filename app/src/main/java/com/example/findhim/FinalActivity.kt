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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.final_layout)

        // Find the three EditText views
        val editText1 = findViewById<EditText>(R.id.first_name)
        val editText2 = findViewById<EditText>(R.id.last_name)
        val editText3 = findViewById<EditText>(R.id.email)

        //TODO: When repetition enabled, stats per run.
        val time = intent.getStringExtra("time")
        val clicks = intent.getStringExtra("clicks")
        findViewById<TextView>(R.id.attempts).append(clicks)
        findViewById<TextView>(R.id.totaltime).append(time)

        // Play again button
        val playAgain = findViewById<Button>(R.id.playagain_button)
        playAgain.setOnClickListener {
            //TODO
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        // Set a button click listener to do something with the user input
        val button = findViewById<Button>(R.id.submit_button)
        button.setOnClickListener {
            val firstName = editText1.text.toString()
            val secondName = editText2.text.toString()
            val address =
                editText3.text.toString() //TODO: Check if is a valid address (xxx@yyy.zzz)

            // Do something with the user input
            // For example, you can show a Toast message with the input values
            val message = "Input 1: $firstName, Input 2: $secondName, Input 3: $address"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            //Send email
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
                putExtra(Intent.EXTRA_SUBJECT, "Logs FindHim")
                putExtra(Intent.EXTRA_TEXT, firstName + secondName)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK //Ensure the address is filled
            }
            //Check if email client installed in the device
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No email client installed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
