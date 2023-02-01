package com.route.todoappc37.ui.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.route.todoappc37.R
import com.route.todoappc37.ui.home.fragments.AddBottomSheetFragment
import com.route.todoappc37.ui.home.fragments.ListFragment
import com.route.todoappc37.ui.home.fragments.SettingsFragment

class HomeActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    lateinit var fab: FloatingActionButton
    var listFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNav = findViewById(R.id.bottomNavigation)
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            val addBottomSheet = AddBottomSheetFragment()
            addBottomSheet.onAddClicked = object :AddBottomSheetFragment.OnAddClicked{
                override fun onClick() {
                    listFragment.refreshTodos()
                }
            }
            addBottomSheet.show(supportFragmentManager, "")
        }
        pushFragment(listFragment)
        bottomNav.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.listItem) {
                    pushFragment(listFragment)
                } else {
                    pushFragment(SettingsFragment())
                }
                return true
            }
        })
    }

    fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}