package com.example.storage

import android.R.attr
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
import android.app.Activity

import android.R.attr.data
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var db: AppDatabase? = null
    private var tankDao: TankDAO? = null
    private var tanks = ArrayList<Tank>()
    private lateinit var adapter: TankAdapter
    private var LAUNCH_SECOND_ACTIVITY = 1

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        db = AppDatabase.getInstance(this)
        tankDao = db?.tankDao()
        tanks.clear()

        setInitialData()




        adapter = TankAdapter(this, tanks)

        binding!!.list.adapter = adapter


        binding!!.addItemFab.setOnClickListener {
            navigateToAddItem()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === LAUNCH_SECOND_ACTIVITY) {
            if (resultCode === RESULT_OK) {
                val tank: Tank = data?.extras?.getSerializable("newTank") as Tank
                addItemToDB(tank)
            }
            if (resultCode === RESULT_CANCELED) {

            }
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var columnName = getColumnNameById(prefs.getString("sortTanksByListPreference", "0"))
        changeSortOrder(columnName)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAddItem() {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY)
    }

    fun setInitialData() {

        GlobalScope.launch {
//            tankDao?.clearAll()
//            try {
//                tankDao?.addTank(Tank("Tiger", 1941, "Германия"))
//                tankDao?.addTank(Tank("T34", 1939, "СССР"))
//                tankDao?.addTank(Tank("M4 SHerman", 1941, "США"))
//            } catch (e: Exception) {
//                Log.d("TAG", "setInitialData: " + e.stackTrace)
//            }
            loadDataToBD()
        }
    }

    fun changeSortOrder(field: String) {
        GlobalScope.launch {
            tanks = when(field) {
                "Nation" -> ArrayList(tankDao?.getSortedByFieldNation())
                "Name" -> ArrayList(tankDao?.getSortedByFieldName())
                else -> ArrayList(tankDao?.getSortedByFieldYear())
            }
            runOnUiThread {
                adapter.changeDataset(tanks)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun loadDataToBD() {
        tanks = ArrayList(tankDao?.getAll()!!)
        runOnUiThread {
            adapter.changeDataset(tanks)
            adapter.notifyDataSetChanged()
        }
    }

    fun addItemToDB(tank: Tank) {
        GlobalScope.launch {
            try {
                tankDao?.addTank(tank)
            } catch (e: Exception) {
                Log.d("TAG", "setInitialData: " + e.stackTrace)
            }

            loadDataToBD()
        }
    }

    private fun getColumnNameById(string: String?): String {
        return when (string?.toIntOrNull()) {
            1 -> "Year of release"
            2 -> "Nation"
            else -> "Name"
        }
    }
}