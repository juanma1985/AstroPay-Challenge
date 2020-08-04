package com.clean.project.app.ui.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.clean.project.app.R
import com.clean.project.app.domain.models.Weather
import com.clean.project.app.ui.commons.BaseFragment
import com.clean.project.app.ui.commons.extensions.gone
import com.clean.project.app.ui.commons.extensions.visible
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    private val viewModel: DetailsViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_details

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModelObservers()
    }

    override fun onStart() {
        shimmerFrameLayout.startShimmer()
        super.onStart()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            val args = DetailsFragmentArgs.fromBundle(this)
            viewModel.loadWeather(args.cityId)
        }
    }

    private fun initViewModelObservers() {
        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            renderDetails(it)
        })
    }

    private fun renderDetails(it: Weather) {
        hideShimmer()
        Glide.with(this)
            .load(it.info.icon)
            .fitCenter()
            .into(image)

        city_name.text = it.city.name
        title.text = it.info.description


        temp.text = getString(R.string.degrees, it.main.temp)
        temp_min.setValue(getString(R.string.degrees, it.main.tempMin))
        temp_max.setValue(getString(R.string.degrees, it.main.tempMax))

        wind.setValue(getString(R.string.speed, it.info.windSpeed.toString()))
        humidity.setValue(getString(R.string.humidity, it.main.humidity))
        sunrise.setValue(it.hours.sunrise)
        sunset.setValue(it.hours.sunset)
    }

    private fun hideShimmer() {
        detailsContainer.visible()
        shimmerFrameLayout.gone()
        shimmerFrameLayout.stopShimmer()
    }

}