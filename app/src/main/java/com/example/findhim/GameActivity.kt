package com.example.findhim

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*


class GameActivity : AppCompatActivity() {
    private lateinit var gridView: GridView
    private lateinit var textInput1: TextView
    private lateinit var gridContainer: FrameLayout
    private lateinit var backgroundImage: Drawable
    private lateinit var bgImage: ImageView
    private lateinit var chronometer: Chronometer
    private var cellSize: Int = 0
    private var clicks: Int = 0
    private var imageIndex: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        gridView = findViewById(R.id.gridView)
        textInput1 = findViewById(R.id.text_input1)
        gridContainer = findViewById(R.id.grid_container)

        cellSize = resources.getInteger(R.integer.cell_size)

        // Get the input values passed from the MainActivity

        bgImage = findViewById(R.id.map)
        val backgroundImageId = intent.getIntExtra(SELECTED_LEVEL_IMAGE_KEY, 0)
        backgroundImage = ContextCompat.getDrawable(this, backgroundImageId)!!
        bgImage.setImageResource(backgroundImageId)

    }

    override fun onResume() {
        var finalHeight = 0
        var finalWidth = 0
        val message = intent.getStringExtra(MESSAGE_KEY)

        //We will use a listener to wait for the image to be drawn and calculate the cells
        val vto: ViewTreeObserver = bgImage.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // Remove after the first run so it doesn't fire forever
                bgImage.viewTreeObserver.removeOnPreDrawListener(this)

                finalHeight = bgImage.height
                finalWidth = bgImage.width
                val numCols = finalWidth / cellSize

                gridView.columnWidth = cellSize
                val numRows = finalHeight / cellSize
                val numCells = numRows * numCols


                // Display the input values in the UI
                textInput1.text =
                    "Welcome to the Game! $message, gridContainer width: ${finalWidth} , gridContainer height: ${finalHeight} "

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

                    override fun getView(
                        position: Int,
                        convertView: View?,
                        parent: ViewGroup?
                    ): View {
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
                gridView.stretchMode = GridView.STRETCH_COLUMN_WIDTH
                gridView.adapter = adapter

                // Set onItemClick Listener for each cell to detect when the user clicks on it
                gridView.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        clicks++
                        // If the user clicks on the image, display a message and finish the activity
                        if (position == imageIndex) {
                            Toast.makeText(this@GameActivity, "You win!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@GameActivity, FinalActivity::class.java)

                            setLogs(intent)
                            chronometer.stop()
                            startActivity(intent)
                        } else {
                            // Do something else if the user clicked on a cell without the letter
                            // For example, you can show a Toast message
                            Toast.makeText(this@GameActivity, "Try again!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                chronometer = findViewById(R.id.chronometer)
                chronometer.start()

                return true
            }
        })


        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometer.stop()
    }

    private fun setLogs(intent: Intent): Intent {
        intent.putExtra("clicks", clicks.toString())
        intent.putExtra("time", chronometer.text.toString())
        return intent
    }

    companion object {
        const val MESSAGE_KEY = "message"
        const val SELECTED_LEVEL_IMAGE_KEY = "selectedLevelImage"
    }
}