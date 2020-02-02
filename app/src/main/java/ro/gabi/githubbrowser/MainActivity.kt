package ro.gabi.githubbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    private val topLevelDestinations = setOf(R.id.repositoryListFragment)

    private val appBarConfiguration by lazy {
        AppBarConfiguration.Builder(topLevelDestinations).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    private fun setupActionBar() {
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }


}
