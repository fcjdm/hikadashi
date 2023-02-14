package com.android.hikadashi


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.android.hikadashi.ui.login.AuthActivity
import com.android.hikadashi.ui.mylist.MyListFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class HostActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    val email = FirebaseAuth.getInstance().currentUser?.email.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)



        drawer = findViewById(R.id.drawer_layout)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val headerView = navigationView.getHeaderView(0)
        val emailText = headerView.findViewById<TextView>(R.id.userText)

        val menuDrawer: ImageView = findViewById(R.id.drawer_toggler)
        menuDrawer.setOnClickListener(View.OnClickListener {
            drawer.openDrawer(GravityCompat.START)
        })


        emailText.text = email
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_discover -> {
                findNavController(R.id.fragment_container_view).navigate(R.id.apiFragment)
            }

            R.id.nav_item_mylist -> {
                findNavController(R.id.fragment_container_view).navigate(R.id.myListFragment)
            }
            R.id.nav_item_logoff -> {

                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}