package org.d3if00001.catatanapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import org.d3if00001.catatanapp.databinding.ActivityMainBinding
import org.d3if00001.catatanapp.presentations.ui.DetailFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        if(fragment is DetailFragment){
            fragment.onBackPressed()
        }else{
            super.onBackPressed()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            if (view.id == R.id.fab) {
                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment_content_main)

                val navController = navHostFragment?.findNavController()
                val currentDestinationId = navController?.currentDestination?.id

                if(currentDestinationId == R.id.detailFragment){
                    navController.navigate(R.id.action_detailFragment_to_noteAddFragment)
                }

                if(currentDestinationId == R.id.listNotesFragment){
                    navController.navigate(R.id.action_listNotesFragment_to_noteAddFragment)
                }

            }
        }
    }
}