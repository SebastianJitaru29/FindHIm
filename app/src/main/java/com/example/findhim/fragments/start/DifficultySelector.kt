package com.example.findhim.fragments.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)

        tinyButton.setOnClickListener { setSelected(tinyButton, view) }
        smallButton.setOnClickListener { setSelected(smallButton, view) }
        mediumButton.setOnClickListener { setSelected(mediumButton, view) }
        bigButton.setOnClickListener { setSelected(bigButton, view) }
        confirmButton.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }
    }

//    private fun showImage(imageResId: Int) {
//        // Display the selected image
//        // For simplicity, assuming you have an ImageView with id 'imageView' in your activity layout
//        activity?.imageView?.setImageResource(imageResId)
//    }

    private fun setSelected(buttonId: Button, view: View) {
        val startActivity = activity as? StartActivity
        startActivity?.setButtonBackground(buttonId)

        val displayImage = view.findViewById<ImageView>(R.id.display_image)
        displayImage.setImageResource(R.drawable.wally)

    }
}