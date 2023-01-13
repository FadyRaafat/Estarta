package com.fady.estarta.presentation.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fady.estarta.R
import com.fady.estarta.databinding.FragmentSplashBinding
import com.fady.estarta.presentation.viewmodels.StartViewModel
import com.fady.estarta.utils.base.BaseFragment
import com.fady.estarta.utils.common.AnimatorUtils
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import utils.common.displayWidth
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class FragmentSplash : BaseFragment<FragmentSplashBinding, StartViewModel>() {

    override fun layout(): Int = R.layout.fragment_splash

    override
    val viewModel: StartViewModel
            by viewModels()


    override fun FragmentSplashBinding.initializeUI() {
        binding.apply {
            val logoHalf1Anim = ObjectAnimator.ofPropertyValuesHolder(
                logoHalf1,
                AnimatorUtils.translationX(-requireActivity().displayWidth.toFloat(), 10f),
                AnimatorUtils.alpha(0f, 1f)
            ).setDuration(500)

            val logoHalf1BackBAnim = ObjectAnimator.ofPropertyValuesHolder(
                logoHalf1,
                AnimatorUtils.translationX(10f, 0f)
            ).setDuration(50)

            val logoHalf2Anim = ObjectAnimator.ofPropertyValuesHolder(
                logoHalf2,
                AnimatorUtils.translationX(requireActivity().displayWidth.toFloat(), -10f),
                AnimatorUtils.alpha(0f, 1f)
            ).setDuration(500)

            val logoHalf2BackBAnim = ObjectAnimator.ofPropertyValuesHolder(
                logoHalf2,
                AnimatorUtils.translationX(-10f, 0f)
            ).setDuration(50)

            AnimatorSet().apply {
                duration = 900
                playTogether(
                    logoHalf1Anim,
                    logoHalf2Anim
                )
                play(logoHalf1BackBAnim).after(logoHalf1Anim)
                play(logoHalf2BackBAnim).after(logoHalf2Anim)
                doOnEnd {
                    setupSplashObserver()
                }
                start()
            }


        }
    }

    @SuppressLint("CheckResult")
    private fun setupSplashObserver() {
        Observable.timer(1.toLong(), TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                view?.post {
                    toSearchScreen()
                }
            }
    }

    private fun toSearchScreen() {
        findNavController().navigate(FragmentSplashDirections.actionFragmentSplashToFragmentSearch())

    }


}