package com.example.digitallibrary.register

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.digitallibrary.R
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //private lateinit var drawerLayoutReg: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("rrrr","1")
        super.onCreate(savedInstanceState)
        Log.d("rrrr","1.1")
        setContentView(R.layout.activity_main)
        Log.d("rrrr","1.2")
        //drawerLayoutReg = findViewById<DrawerLayout>(R.id.activity_main)

        //val navigationView = findViewById<NavigationView>(R.id.nav_graph_register)
        //navigationView.setNavigationItemSelectedListener(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        Log.d("rrrr","2")
        navController = navHostFragment.navController
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("rrrr","3")
        when (item.itemId) {
            R.id.signupFragment-> navController.navigate(R.id.action_vhodFragment_to_signupFragment)
            R.id.vhodFragment-> navController.navigate(R.id.action_signupFragment_to_vhodFragment)
            R.id.forgotFragment-> navController.navigate(R.id.action_vhodFragment_to_forgotFragment)
        }
        Log.d("rrrr","4")
        //drawerLayoutReg.closeDrawer(GravityCompat.START)
        return true

    }
}