package com.example.storage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.storage.databinding.ActivityMainBinding

import android.content.Intent
import android.util.Log
import com.example.storage.entities.Tank
import com.example.storage.settings.SettingsActivity

import com.example.storage.dao.AppDatabase
import com.example.storage.dao.TankDAO
import com.example.storage.presentation.TankAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var db: AppDatabase? = null
    private var tankDao: TankDAO? = null
    private var tanks = ArrayList<Tank>()
    private lateinit var adapter: TankAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        db = AppDatabase.getInstance(this)
        tankDao = db?.tankDao()

        setInitialData()    //STUB, replace with retrieving data from db


        tanks.clear()



        adapter = TankAdapter(this, tanks)

        binding!!.list.adapter = adapter


        binding!!.addItemFab.setOnClickListener {
            //TODO add item in data base
            GlobalScope.launch {
                tanks = ArrayList(tankDao?.getAll()!!)
                runOnUiThread({
                    adapter.changeDataset(tanks)
                    adapter.notifyDataSetChanged()
                })

            }

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

        GlobalScope.launch {
            tankDao?.clearAll()
            try {
                tankDao?.addTank(Tank(1, "Tiger", 1941, "Германия"))
                tankDao?.addTank(Tank(2, "T34", 1939, "СССР"))
                tankDao?.addTank(Tank(3, "M4 SHerman", 1941, "США"))
            }catch (e: Exception)
            {
                Log.d("TAG", "setInitialData: " + e.stackTrace)
            }
        }

        }
}