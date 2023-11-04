package com.example.course999.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.course999.dashboard.DashboardFragment
import com.example.course999.subscription.SubscriptionFragment

interface Screen {
    fun show(fragmentManager: FragmentManager, containerId: Int)

    abstract class Replace(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.newInstance())
                .addToBackStack(fragmentClass.name )
                .commit()
        }
    }

    abstract class Add(private val fragmentClass: Class<out Fragment>) : Screen {
        override fun show(fragmentManager: FragmentManager, containerId: Int) {
            fragmentManager.beginTransaction()
                .add(containerId, fragmentClass.newInstance())
                .commit()
        }
    }

    object Dashboard : Replace(DashboardFragment::class.java) {
    }

    object Subscription : Replace(SubscriptionFragment::class.java) {
    }
}