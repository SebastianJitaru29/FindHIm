package com.example.findhim.fragments.StartActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.findhim.R
import com.example.findhim.StartActivity


class DifficultySelector : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_difficulty_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tinyButton = view.findViewById<Button>(R.id.tinyButton)
        val smallButton = view.findViewById<Button>(R.id.smallButton)
        val mediumButton = view.findViewById<Button>(R.id.mediumButton)
        val bigButton = view.findViewById<Button>(R.id.bigButton)

        tinyButton.setOnClickListener { setSelected(tinyButton) }
        smallButton.setOnClickListener { setSelected(smallButton) }
        mediumButton.setOnClickListener { setSelected(mediumButton) }
        bigButton.setOnClickListener { setSelected(bigButton) }
    }

//    private fun showImage(imageResId: Int) {
//        // Display the selected image
//        // For simplicity, assuming you have an ImageView with id 'imageView' in your activity layout
//        activity?.imageView?.setImageResource(imageResId)
//    }

    private fun setSelected(buttonId: Button) {
        val startActivity = activity as? StartActivity
        startActivity?.setButtonBackground(buttonId)
    }
}