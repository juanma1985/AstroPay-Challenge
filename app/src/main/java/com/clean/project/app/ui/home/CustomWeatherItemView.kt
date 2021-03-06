package com.clean.project.app.ui.home

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.clean.project.app.R
import kotlinx.android.synthetic.main.custom_weather_item.view.*

class CustomWeatherItemView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        inflate(context, R.layout.custom_weather_item, this)
        val title: TextView = findViewById(R.id.title)
        val value: TextView = findViewById(R.id.value)

        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.CustomWeatherItemView)
            title.text = attributes.getString(R.styleable.CustomWeatherItemView_title)
            value.text = attributes.getString(R.styleable.CustomWeatherItemView_value)

            attributes.recycle()
        }
    }

    fun setValue(text: String) {
        value.text = text
    }

}