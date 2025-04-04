package com.example.digitallibrary.offline

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import com.example.digitallibrary.R
import com.example.digitallibrary.fragment.AboutAppFragment
import com.example.digitallibrary.fragment.ArchivFragment
import com.example.digitallibrary.fragment.BookmarkFragment
import com.example.digitallibrary.fragment.ChoiceFragment
import com.example.digitallibrary.fragment.ImportWorkFragment
import com.example.digitallibrary.fragment.LikeFragment
import com.example.digitallibrary.fragment.NewWorkFragment
import com.example.digitallibrary.fragment.profile.ProfilFragment
import com.example.digitallibrary.fragment.SettingFragment
import com.example.digitallibrary.register.MainActivity
import com.google.android.material.navigation.NavigationView

class MenuOfflineActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_offline)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null//отключение заголовка у toolbar
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        /*
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                navController = navHostFragment.navController
                */

                if (savedInstanceState == null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainMenuOfflineFragment()).commit()
                    navigationView.setCheckedItem(R.id.nav_main_menu_offline)
                }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingFragment()).commit()
            R.id.nav_about -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AboutAppFragment()).commit()
            R.id.nav_archiv -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ArchivFragment()).commit()
            R.id.nav_bookmark -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BookmarkFragment()).commit()
            R.id.nav_choice -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ChoiceFragment()).commit()
            R.id.nav_import -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ImportWorkFragment()).commit()
            R.id.nav_new_work -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewWorkFragment()).commit()
            R.id.nav_profil -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfilFragment()).commit()
            R.id.nav_like -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LikeFragment()).commit()
            R.id.nav_logout -> {
                // Переход в MainActivity при выходе
                startActivity(Intent(this, MainActivity::class.java))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Очистка стека задач
                //startActivity(intent)
                finish() // Закрыть текущую активность
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack() // Возвращаемся к предыдущему фрагменту
        } else {
            super.onBackPressed() // Завершаем активность, если стек пуст
        }
    }

}