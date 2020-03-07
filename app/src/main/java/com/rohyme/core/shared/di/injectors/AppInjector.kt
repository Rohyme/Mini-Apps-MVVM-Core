package com.rohyme.core.shared.di.injectors

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.rohyme.core.shared.appUtils.ConnectionStatus
import com.rohyme.core.shared.appUtils.baseUtils.HasConnectivityActivity
import com.rohyme.core.shared.appUtils.baseUtils.HasLanguageObserver
import com.rohyme.core.shared.appUtils.networkUtils.NetworkUtils
import com.rohyme.core.shared.di.components.DaggerApplicationComponent
import com.rohyme.core.shared.myApp.MyApplication
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector

/**
 * Helper class to automatically inject fragments if they implement [Injectable].
 */
object AppInjector {
    fun init(application: MyApplication) {
        DaggerApplicationComponent.factory().create(application).injectApp(application)

        application
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                        handleActivity(activity)
                        if (activity is HasConnectivityActivity && activity is LifecycleOwner) {
                            activity.handleConnectivity(activity)
                        }
                        if (activity is HasLanguageObserver && activity is AppCompatActivity) {
                            activity.handleLanguageChange(activity)
                        }
                    }

                    override fun onActivityStarted(activity: Activity) {

                    }

                    override fun onActivityResumed(activity: Activity) {

                    }

                    override fun onActivityPaused(activity: Activity) {

                    }

                    override fun onActivityStopped(activity: Activity) {

                    }

                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

                    }

                    override fun onActivityDestroyed(activity: Activity) {

                    }
                })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentCreated(
                                        fm: FragmentManager,
                                        f: Fragment,
                                        savedInstanceState: Bundle?
                                ) {
                                    if (f is FragmentWithInjector<*>) {
                                        f.injectVM(f)
                                    }
                                }
                            }, true
                    )
        }
    }

    private fun HasConnectivityActivity.handleConnectivity(lifecycleOwner: LifecycleOwner) {
        NetworkUtils.networkStatus.observe(lifecycleOwner, Observer {
            when (it) {
                ConnectionStatus.CONNECTED -> {
                    onConnected()
                }
                ConnectionStatus.NOTCONNECTED -> {
                    onDisconnected()
                }
                else -> onDisconnected()
            }
        })
    }

    private fun HasLanguageObserver.handleLanguageChange(activity: AppCompatActivity) {
        if (languageUtils == null) return
        languageUtils?.currentLanguage?.observe(activity, Observer {
            languageUtils?.setLocale(activity, it)
            activity.startActivity(activity.intent)
            activity.finish()
        })
    }
}
