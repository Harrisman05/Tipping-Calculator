package com.hharris.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.util.Log

/*
Activity means one screen in android terminology, contains business
logic of the app.

onCreate is automatically invoked when the application is started.

setContentView(R.layout.activity.main) - this means:

    - The content (UI) of the MainActivity (screen) should be set to
      R.layout.activity.main (R means resources, also activity.main is xml file)

 */

/*

ConstraintLayout allows you to create large and complex layout with a flat view hierachy
(no nested view groups).

ConstraintLayout is the parent element of the TextView child

When creating components in activity.xml, they must be constrained vertically and horizontally
otherwise there will be a compiler error. For the first component, drag the constraint circles
of the component to the edge of the screen to constrain them. Then add margin to offset them
from the side of the screen in the constraint widget.

Drag anchor points to constrain/group them to other components, then set the offset values in
constraint widget with margin


 */

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15 // constant for inital Tip Percent

class MainActivity : AppCompatActivity() {

    // Lateinit means initialise variable in onCreate method and not the constructor

    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercentLabel: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign the lateinit variables to the components in UI using their ids

        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercentLabel = findViewById(R.id.tvTipPercentLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercentLabel.text = "$INITIAL_TIP_PERCENT%"

        // Add eventListener
        /*
            Have to override the setOnSeekBarChangeListener. Using anonymous class allows you to
            override the functions using an expression, without having to having to create a new
            class.
        */
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChange $progress")
                /* Convention for logging is that the TAG is the name of the activity file being
                debugged */

                tvTipPercentLabel.text = "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        // Override always

        etBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "after text has been changed $s")
                computeTipAndTotal()
            }

        })

    }

    private fun computeTipAndTotal() {

        // 1) Get value of the base and tip percent
        // 2) Compute the tip and total
        // 3) Update UI

        if (etBaseAmount.text.isEmpty()) { // early return from function is the field is empty,
            // program will crash otherwise, also reset values of tip amount and total amount
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }

        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercentage = seekBarTip.progress

        Log.i(TAG, "$baseAmount, $tipPercentage")

        val tip = baseAmount * tipPercentage / 100
        tvTipAmount.text = "%.2f".format(tip)

        val totalAmount = baseAmount + tip
        tvTotalAmount.text = "%.2f".format(totalAmount)
    }

}


