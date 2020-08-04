package com.clean.project.app.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.clean.project.app.R
import com.clean.project.app.domain.models.City
import com.clean.project.app.ui.commons.BaseFragment
import com.clean.project.app.ui.home.adapter.CitiesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var selectedCity: City
    private lateinit var citiesAdapter: CitiesAdapter

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModelObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManager = LinearLayoutManager(requireContext())
        recycler.apply {
            citiesAdapter = CitiesAdapter { city -> onCitySelected(city) }
            layoutManager = viewManager
            adapter = citiesAdapter
        }
    }

    private fun onCitySelected(city: City) {
        selectedCity = city
        if (city.id != CURRENT_LOCATION) {
            viewModel.cityClicked(selectedCity)
        } else {
            checkOpenPermission()
        }
    }

    private fun initViewModelObservers() {
        viewModel.citiesData.observe(viewLifecycleOwner, Observer {
            citiesAdapter.submitList(it)
        })
        viewModel.openDetails.observe(viewLifecycleOwner, Observer {
            it?.getContentIfNotHandled()?.run {
                openDetailsScreen(this)
            }
        })
    }

    private fun openDetailsScreen(cityId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(cityId)
        findNavController().navigate(action)
    }

    private fun checkOpenPermission() {
        if (checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                Snackbar.make(
                    requireView(),
                    getString(R.string.permissions),
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Ok, allow") {
                        requestLocationPermission()
                    }
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                requestLocationPermission()
            }
        } else {
            viewModel.cityClicked(selectedCity)
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the task you need to do.
                    viewModel.cityClicked(selectedCity)
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    companion object {
        private const val CURRENT_LOCATION = -1
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 400
    }
}

