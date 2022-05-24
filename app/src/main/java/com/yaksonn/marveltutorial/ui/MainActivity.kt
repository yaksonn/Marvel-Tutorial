package com.yaksonn.marveltutorial.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.yaksonn.marveltutorial.R
import com.yaksonn.marveltutorial.common.base.BaseActivity
import com.yaksonn.marveltutorial.databinding.ActivityMainBinding
import com.yaksonn.marveltutorial.ui.characters.CharactersFragment
import com.yaksonn.marveltutorial.ui.detail.CharacterDetailFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController
    private lateinit var analytics: FirebaseAnalytics

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initUI() {
        analytics = Firebase.analytics

        setSupportActionBar(binding.toolbar)
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onBackPressed() {
        val label = navController.currentDestination?.label
        if (label != CharactersFragment::class.java.simpleName &&
            (label == CharacterDetailFragment::class.java.simpleName)
        ) {
            navController.popBackStack(R.id.charactersFragment, false)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}