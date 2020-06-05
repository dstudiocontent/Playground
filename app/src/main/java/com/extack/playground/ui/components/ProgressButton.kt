package com.extack.playground.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.extack.playground.R
import com.extack.playground.databinding.ProgressButtonBinding
import com.google.android.material.button.MaterialButton


@Suppress("UNUSED")
class ProgressButton : ConstraintLayout {

    private lateinit var binding: ProgressButtonBinding
    private lateinit var button: MaterialButton
    private lateinit var progressIndicator: ProgressBar
    private var buttonText = ""

    constructor(context: Context) : super(context) {
        initLayout(context)
    }

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        initLayout(context)
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.ProgressButton)
        buttonText = a.getString(R.styleable.ProgressButton_button_text) ?: "Progress Button"
        a.recycle()
    }

    private fun initLayout(context: Context) {
        binding =
            ProgressButtonBinding.inflate(LayoutInflater.from(context), this, true)

        /*val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.progress_button, this)*/
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        button = binding.progressButton//findViewById(R.id.progress_button)
        progressIndicator =
            binding.progressButtonIndicator//findViewById(R.id.progress_button_indicator)

        button.text = buttonText

        button.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                refresh()
                button.performClick()
            }
            true
        }
    }

    private fun refresh() {
        button.text = ""
        progressIndicator.visibility = View.VISIBLE
        button.isEnabled = false
    }

    fun progressCompleted() {
        button.text = buttonText
        progressIndicator.visibility = View.GONE
        button.isEnabled = true
    }

    fun setButtonOnClickListener(listener: OnClickListener) {
        button.setOnClickListener(listener)
    }
}