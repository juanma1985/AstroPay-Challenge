package com.clean.project.app.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.clean.project.app.domain.models.City

class CitiesAdapter(
    private val itemClickListener: (item: City) -> Unit
) : ListAdapter<City, CityViewHolder>(
    DiffCityCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder.create(
            parent,
            itemClickListener
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class DiffCityCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }
}