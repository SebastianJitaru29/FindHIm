package com.example.findhim

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.Integer.min
import java.util.*


class GameActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var textInput1: TextView
    private lateinit var gridContainer: FrameLayout

    private var imageIndex: Int = -1

    private lateinit var chronometer: Chronometer
    private var clicks: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        gridView = findViewById(R.id.gridView)
        textInput1 = findViewById(R.id.text_input1)
        gridContainer = findViewById(R.id.grid_container)

        // Get the input values passed from the MainActivity
        val message = intent.getStringExtra(MESSAGE_KEY)

        // Calculate the cell size based on the screen width and height
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val cellSize = min(screenWidth, screenHeight) / 10 // You can adjust this value to suit your needs

        // Calculate the number of rows and columns based on the screen size and the cell size
        val numCols = 10
        val numRows = 16
        val numCells = numRows * numCols

        // Display the input values in the UI
        textInput1.text =
            "Welcome to the Game! $message, gridContainer width: ${gridContainer.width} , gridContainer height: ${gridContainer.height} "

        // Create a list of cell values
        val random = Random()
        if (imageIndex == -1) {
            imageIndex = random.nextInt(numCells)
        }
        val cellValues =
            Array(numCells) { if (it == imageIndex) R.drawable.wally else R.drawable.transparent_square }

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
                    imageView.layoutParams = ViewGroup.LayoutParams(cellSize, cellSize)
                    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                    imageView.setPadding(8, 8, 8, 8)
                } else {
                    imageView = convertView as ImageView
                }

                imageView.setImageResource(cellValues[position])
                return imageView
            }
        }
        // Set the number of rows and columns of the grid based on the calculated values
        gridView.numColumns = numCols
        gridView.verticalSpacing = 8
        gridView.horizontalSpacing = 8
        gridView.stretchMode = GridView.STRETCH_COLUMN_WIDTH
        gridView.adapter = adapter

        // Set onItemClick Listener for each cell to detect when the user clicks on it
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            clicks++
            // If the user clicks on the image, display a message and finish the activity
            if (position == imageIndex) {
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FinalActivity::class.java)

                setLogs(intent)
                chronometer.stop()
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

        chronometer = findViewById(R.id.chronometer)
        chronometer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometer.stop()
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

    private fun setLogs(intent: Intent): Intent {
        intent.putExtra("clicks", clicks.toString())
        intent.putExtra("time", chronometer.text.toString())
        return intent
    }

    companion object {
        const val MESSAGE_KEY = "message"
    }
}