package com.example.practise.ui.dashboard


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practise.ui.login.LoginActivity
import com.example.practise.R
import com.example.practise.helper.SharedPreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class DashBoard : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var auth : FirebaseAuth
    private lateinit var sharePref : SharedPreferenceManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        auth= FirebaseAuth.getInstance()
        sharePref = SharedPreferenceManager(this)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_addinfo,
                R.id.nav_adduser,
                R.id.nav_showinfo
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dash_board, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId== R.id.signout_id)
        {
            auth.signOut()
            sharePref.setLoginStatus(false)
            finish()
            startActivity(Intent(this, LoginActivity::class.java))

        }

        if(item.itemId== R.id.whitecolor_id)
        {
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            drawerLayout.setBackgroundColor(Color.WHITE)
        }

        if(item.itemId== R.id.graycolor_id)
        {
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            drawerLayout.setBackgroundColor(Color.GRAY)
        }

        if(item.itemId== R.id.nav_adduser)
        {
            Toast.makeText(applicationContext,"Selected",Toast.LENGTH_LONG).show()
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {

        if(findNavController(R.id.nav_host_fragment).currentDestination?.id== R.id.nav_addinfo)
        {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        else
        {
            super.onBackPressed()
          }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
