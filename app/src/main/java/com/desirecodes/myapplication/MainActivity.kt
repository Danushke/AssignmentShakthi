package com.desirecodes.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.desirecodes.myapplication.extention.replaceFragment
import com.desirecodes.myapplication.ui.home.HomeFragment
import com.desirecodes.myapplication.ui.more.MoreFragment
import com.desirecodes.myapplication.ui.notifications.AboutMeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val abc = findViewById<BottomNavigationView>(R.id.nav_view)
        val abcd = findViewById<FragmentContainerView>(R.id.nav_host_fragment_activity_main)
        abc.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.replaceFragment(
                        abcd.id,
                        HomeFragment.newInstance(),
                        HomeFragment.TAG
                    )

                }
                R.id.navigation_about_me -> {
                    supportFragmentManager.replaceFragment(
                        abcd.id,
                        AboutMeFragment.newInstance(),
                        AboutMeFragment.TAG
                    )
                }
                R.id.navigation_more -> {
                    supportFragmentManager.replaceFragment(
                        abcd.id,
                        MoreFragment.newInstance(),
                        MoreFragment.TAG
                    )
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
