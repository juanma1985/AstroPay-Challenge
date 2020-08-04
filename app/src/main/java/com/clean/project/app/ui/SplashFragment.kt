package com.clean.project.app.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.clean.project.app.R
import com.clean.project.app.ui.commons.BaseFragment


class SplashFragment : BaseFragment() {

    private lateinit var handler: DelayedAction

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = DelayedAction(Runnable { openHome() }, 1500)
    }

    private fun openHome() {
        val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onPause() {
        handler.cancel()
        super.onPause()
    }

}

class DelayedAction(runnable: Runnable?, delay: Long) {
    private val _handler: Handler?
    private val _runnable: Runnable?

    /**
     * Cancel a runnable
     */
    fun cancel() {
        if (_handler == null || _runnable == null) {
            return
        }
        _handler.removeCallbacks(_runnable)
    }

    /**
     * Constructor
     * @param runnable The runnable
     * @param delay The delay (in milli sec) to wait before running the runnable
     */
    init {
        _handler = Handler(Looper.getMainLooper())
        _runnable = runnable
        _handler.postDelayed(_runnable, delay)
    }
}