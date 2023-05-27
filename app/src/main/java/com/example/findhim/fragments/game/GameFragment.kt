package com.example.findhim.fragments.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Chronometer
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.findhim.FinalActivity
import com.example.findhim.R
import com.example.findhim.databinding.FragmentGameBinding
import com.example.findhim.game.GameActivity
import com.example.findhim.persistency.Game
import com.example.findhim.persistency.GameApplication
import com.example.findhim.persistency.GameViewModel
import com.example.findhim.persistency.GameViewModelFactory
import java.util.Random


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var gameActivity: GameActivity
    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((requireActivity().application as GameApplication).repository)
    }


    private lateinit var gridView: GridView
    private lateinit var bgImage: ImageView
    private lateinit var chronometer: Chronometer

    private var backgroundImageId: Int = 0
    private var cellSize: Int = 100
    private var clicks: Int = 0
    private var imageIndex: Int = -1
    private var numCols: Int = 0
    private var numRows: Int = 0

    private var mListener: GameFragmentListener? = null

    private var message: String? = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as GameFragmentListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement GameFragmentListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameActivity = requireActivity() as GameActivity

        arguments?.let {
            cellSize = it.getInt(ARG_CELL_SIZE, 100)
            backgroundImageId = it.getInt(ARG_MAP, 0)
            message = it.getString(ARG_NICKNAME)
        }

        gridView = binding.gridView
        bgImage = binding.map

        bgImage.setImageResource(backgroundImageId)

        if (savedInstanceState != null) {
            imageIndex = savedInstanceState.getInt("waldoPos", -1)
        }

        createGrid()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //waldo pos
        outState.putInt("waldoPos", imageIndex)

    }

    private fun createGrid() {
        //Listener to wait for the image to be drawn and calculate the cells
        val vto: ViewTreeObserver = bgImage.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // Remove after the first run so it doesn't fire forever
                bgImage.viewTreeObserver.removeOnPreDrawListener(this)
                val finalHeight = bgImage.height
                val finalWidth = bgImage.width

                if (numCols == 0 && numRows == 0) {
                    numCols = finalWidth / cellSize
                    numRows = finalHeight / cellSize
                }
                val numCells = numRows * numCols
                Log.e(
                    "GameFragment",
                    "$numCells $numCols $numRows $cellSize $backgroundImageId"
                )

                gridView.columnWidth = cellSize


                // Create a list of cell values
                if (imageIndex == -1) {
                    val random = Random()
                    imageIndex = random.nextInt(numCells)
//                    imageIndex = random.nextInt(10)
                }

                val cellValues = createCells(numCells)
                val adapter = createAdapter(cellValues)

                // Set the number of rows and columns of the grid based on the calculated values
                gridView.numColumns = numCols
                gridView.stretchMode = GridView.NO_STRETCH
                gridView.adapter = adapter
                var toast: Toast? = null

                // Set onItemClick Listener for each cell to detect when the user clicks on it
                gridView.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        mListener?.onUserClick()

                        // If the user clicks on the image, display a message and finish the activity
                        if (position == imageIndex) {
//                            chronometer.stop()
                            mListener?.onStopTime()

                            toast?.cancel()
                            toast =
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.win_text),
                                    Toast.LENGTH_SHORT
                                )
                            toast?.show()

                            val intent = Intent(requireContext(), FinalActivity::class.java)
                            setLogs(intent)//set logs creara bundle con objeto tipo game que es parcelable
                            requireActivity().startActivity(intent)//pass the game object to the next activity
                            requireActivity().finish()
                        } else {
                            // Show toast
                            toast?.cancel()
                            toast =
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.try_again),
                                    Toast.LENGTH_SHORT
                                )
                            toast?.show()
                        }
                    }

                // It doesn't matter if it has already been started
//                gameActivity.chronometer.start()
                mListener?.onStartTime()

                return true
            }
        })
    }

    private fun createCells(numCells: Int): Array<Int> {
        return Array(numCells) { if (it == imageIndex) R.drawable.wally else R.drawable.transparent_square }
    }

    private fun createAdapter(cellValues: Array<Int>): BaseAdapter {
        return object : BaseAdapter() {
            override fun getCount(): Int {
                return cellValues.size
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
                    imageView = ImageView(requireContext())
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
    }

    private fun setLogs(intent: Intent): Intent {
        val game = Game(
            null,
            message,
            mListener?.getAttempts().toString(),
            mListener?.onGetTime()?.text.toString()
        )
        gameViewModel.insert(game)
        //save game in database here
        val bundle = Bundle()
        bundle.putParcelable("game", game)
        intent.putExtras(bundle)
        return intent
    }

    fun update(cellSize: Int, backgroundImageId: Int, nickname: String?) {
        this.cellSize = cellSize
        this.backgroundImageId = backgroundImageId
        this.message = nickname
    }


    companion object {
        //TODO set to strings.xml
        private const val ARG_CELL_SIZE = "arg_cell_size"
        private const val ARG_MAP = "arg_map"
        private const val ARG_NICKNAME = "arg_name"

        fun newInstance(cellSize: Int, backgroundImageId: Int, nickname: String?): GameFragment {
            val fragment = GameFragment()
            val args = Bundle()
            args.putInt(ARG_CELL_SIZE, cellSize)
            args.putInt(ARG_MAP, backgroundImageId)
            args.putString(ARG_NICKNAME, nickname)
            fragment.arguments = args
            return fragment
        }
    }
}