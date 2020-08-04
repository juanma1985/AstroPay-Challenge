package com.clean.project.app.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clean.project.app.R
import com.clean.project.app.domain.models.City
import com.clean.project.app.ui.commons.extensions.inflate
import kotlinx.android.synthetic.main.city_item.view.*

class CityViewHolder(
    view: View,
    itemClickListener: (item: City) -> Unit
) : RecyclerView.ViewHolder(view) {
    private lateinit var item: City

    init {
        view.setOnClickListener { itemClickListener.invoke(item) }
    }

    fun bind(item: City) {
        this.item = item

        itemView.title.text = item.name
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClickListener: (item: City) -> Unit
        ): CityViewHolder {
            val view = parent.inflate(R.layout.city_item)

            return CityViewHolder(
                view = view,
                itemClickListener = itemClickListener
            )
        }
    }
}