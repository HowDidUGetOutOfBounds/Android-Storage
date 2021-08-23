package com.example.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.storage.databinding.ActivityMainBinding

import android.content.Intent
import com.example.storage.entities.Tank
import com.example.storage.settings.SettingsActivity
import com.example.storage.presentation.App

import com.example.storage.dao.AppDatabase
import com.example.storage.presentation.TankAdapter


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var db: AppDatabase? = null
    private var tanks = ArrayList<Tank>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        db = App.getInstance()?.getDatabase()

        setInitialData()    //STUB
        binding!!.list.adapter = TankAdapter(this, tanks)


        binding!!.addItemFab.setOnClickListener {
            //TODO add item in data base
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
      return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_filter) {
            navigateToSettings()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun navigateToSettings(){
        val intent  = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun setInitialData(){

        tanks.add(Tank("T34", 1939, "СССР"))
        tanks.add(Tank("Tiger", 1941,"Германия"))
        tanks.add(Tank("M4 SHerman", 1941,"США"))
        }
}