package com.example.storage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storage.dao.AppDatabase
import com.example.storage.dao.TankDAO
import com.example.storage.databinding.ActivityAddItemBinding
import com.example.storage.databinding.ActivityMainBinding
import com.example.storage.entities.Tank
import com.example.storage.presentation.TankAdapter
import android.app.Activity

import android.content.Intent
import android.widget.Toast
import java.lang.Exception


class AddItemActivity : AppCompatActivity() {

    private var binding: ActivityAddItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        binding?.buttonAddItem?.setOnClickListener {
            var tank = getTank()
            returnNewItem(tank)
        }
    }

    private fun getTank(): Tank? {
        var tank: Tank? = null

        try {
            tank = Tank(
                binding?.name?.text.toString(),
                binding?.year?.text.toString().toInt(),
                binding?.nation?.text.toString()
            )
        } catch (e: Exception) {
            Toast.makeText(this, "wrong input params", Toast.LENGTH_SHORT).show()
        }

        return tank
    }

    private fun returnNewItem(tank: Tank?) {
        if(tank != null) {
            val returnIntent = Intent()
            returnIntent.putExtra("newTank", tank)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}
