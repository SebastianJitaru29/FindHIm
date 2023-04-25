package com.example.findhim

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class GameActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var textInput1: TextView
    private var imageIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        gridView = findViewById(R.id.gridView)
        textInput1 = findViewById(R.id.text_input1)

        // Get the input values passed from the MainActivity
        val message = intent.getStringExtra(MESSAGE_KEY)
        val repetitions = intent.getIntExtra(REPETITIONS_KEY, 0)
        val numRows = intent.getIntExtra(ROWS_KEY, 0)
        val numCols = intent.getIntExtra(COLUMNS_KEY, 0)

        // Display the input values in the UI
        textInput1.text =
            "Welcome to the Game! $message \n You have $repetitions tries, rows: $numRows, columns $numCols"

        // Create a list of cell values
        val numCells = numRows * numCols
        val random = Random()
        imageIndex = random.nextInt(numCells)
        val cellValues =
            Array(numCells) { if (it == imageIndex) R.drawable.wally1 else com.google.android.material.R.drawable.m3_tabs_transparent_background }

        // Create the grid of cells with random values
        val adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return numCells
            }

            override fun getItem(position: Int): Any? {
                return null
            }

            override fun getItemId(position: Int): Long {
                return 0
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val imageView: ImageView
                if (convertView == null) {
                    imageView = ImageView(applicationContext)
                    imageView.layoutParams = ViewGroup.LayoutParams(100, 100)
                    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageView.setPadding(8, 8, 8, 8)
                } else {
                    imageView = convertView as ImageView
                }

                imageView.setImageResource(cellValues[position])
                return imageView
            }
        }
        // Set the number of rows and columns of the grid based on user input
        gridView.numColumns = numCols
        gridView.verticalSpacing = 8
        gridView.horizontalSpacing = 8
        gridView.stretchMode = GridView.STRETCH_COLUMN_WIDTH
        gridView.adapter = adapter

        // Set onItemClick Listener for each cell to detect when the user clicks on it
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // If the user clicks on the image, display a message and finish the activity
            if (position == imageIndex) {
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FinalActivity::class.java)
                startActivity(intent)
            } else {
                // Do something else if the user clicked on a cell without the letter
                // For example, you can show a Toast message
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
            }
        }

        // Set a random image as the background of the grid
        val randomImage = getRandomImage()
        gridView.background = randomImage
    }

    private fun getRandomImage(): Drawable {
        // Create an array of drawable resources for the background images
        val backgroundImages = arrayOf(
            R.drawable.map1,
            R.drawable.map2,
            R.drawable.map3,
            R.drawable.map4,
            R.drawable.map5
        )
        // Choose a random image from the array
        val randomIndex = Random().nextInt(backgroundImages.size)
        return resources.getDrawable(backgroundImages[randomIndex], null)
    }

    companion object {
        const val MESSAGE_KEY = "message"
        const val REPETITIONS_KEY = "repetitions"
        const val ROWS_KEY = "rows"
        const val COLUMNS_KEY = "columns"
    }
}
