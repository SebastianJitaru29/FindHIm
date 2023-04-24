package com.example.findhim

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R

class GameActivity : AppCompatActivity() {

    private lateinit var textInput1: TextView
    private lateinit var textInput2: TextView

    companion object {
        const val MESSAGE_KEY = "message"
        const val REPETITIONS_KEY = "repetitions"
        const val ROWS_KEY = "row"
        const val COLUMNS_KEY = "column"
    }
    private lateinit var tableLayout: TableLayout
    private var cellValues = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        textInput1 = findViewById(R.id.text_input1)
        tableLayout = findViewById(R.id.tableLayout)

        // Get the input values passed from the MainActivity
        val message = intent.getStringExtra(MESSAGE_KEY)
        val repetitions = intent.getIntExtra(REPETITIONS_KEY, 0)
        val numRows = intent.getIntExtra(ROWS_KEY, 0)
        val numCols = intent.getIntExtra(COLUMNS_KEY, 0)
        // Display the input values in the UI
        textInput1.text = "Welcome to the Game! $message you have $repetitions tries, rows: $numRows, columns $numCols"
        // Create a list of cell values
        val numCells = numRows * numCols
        val letterIndex = (0..numCells).random()
        for (i in 0 until numCells) {
            if (i == letterIndex) {
                cellValues.add("W")
            } else {
                cellValues.add((i + 1).toString())
            }
        }
        cellValues.shuffle()

        // Create the grid of cells with random values
        for (i in 0 until numRows) {
            val tableRow = TableRow(this)
            for (j in 0 until numCols) {
                val cell = TextView(this)
                cell.text = cellValues[i * numCols + j]
                cell.setBackgroundResource(R.drawable.grey_rectangle)
                cell.setPadding(30,30,30,30)
                cell.gravity = Gravity.CENTER
                val layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(16, 16, 16, 16) // set margin between the cells
                cell.layoutParams = layoutParams
                tableRow.addView(cell)
            }
            tableLayout.addView(tableRow)
        }

        // Set onClickListener for each cell to detect when the user clicks on it
        for (i in 0 until numRows) {
            val tableRow = tableLayout.getChildAt(i) as TableRow
            for (j in 0 until numCols) {
                val cell = tableRow.getChildAt(j) as TextView
                cell.setOnClickListener {
                    if (cell.text == "W") {
                        val intent = Intent(this, FinalActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Do something when user clicks on non-letter cell
                        Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
