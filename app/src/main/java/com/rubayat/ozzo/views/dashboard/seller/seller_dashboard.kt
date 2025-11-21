package com.rubayat.ozzo.views.dashboard.seller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.rubayat.ozzo.R
import com.rubayat.ozzo.databinding.ActivitySellerDashboardBinding
import com.rubayat.ozzo.views.starter.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class seller_dashboard : AppCompatActivity() {
    private lateinit var binding: ActivitySellerDashboardBinding
    lateinit var navController: NavController

    @Inject
    lateinit var jAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySellerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController=findNavController(R.id.fragmentContainerView3)

        val appBarConfig = AppBarConfiguration(setOf(
            R.id.myProductFragment,
            R.id.uploadFragment,
            R.id.myProfileFragment,
        ))

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfig)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.seller_top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_logout ->{
                jAuth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.menu_report ->{
                Toast.makeText(this,"report Clicked...", Toast.LENGTH_LONG).show()

            }
            R.id.menu_setting ->{
                Toast.makeText(this,"Setting Clicked...", Toast.LENGTH_LONG).show()

            }
        }


        return super.onOptionsItemSelected(item)
    }
}