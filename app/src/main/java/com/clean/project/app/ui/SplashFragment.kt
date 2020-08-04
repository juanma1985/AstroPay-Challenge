package com.clean.project.app.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.clean.project.app.R
import com.clean.project.app.ui.commons.BaseFragment
import com.clean.project.app.ui.home.HomeFragmentDirections


class SplashFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            findNavController().navigate(action)
        }, 1500)
    }

}

