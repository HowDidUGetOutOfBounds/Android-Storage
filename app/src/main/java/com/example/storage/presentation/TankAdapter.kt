package com.example.storage.presentation

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.storage.R
import com.example.storage.entities.Tank
import com.example.storage.utill.getImageResourceFromNation


class TankAdapter internal constructor(context: Context?, tanks: ArrayList<Tank>) :
    RecyclerView.Adapter<TankAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val tanks: ArrayList<Tank> = tanks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.tank_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tank: Tank = tanks[position]
        holder.flagView.setImageResource(getImageResourceFromNation(tank))
        holder.nameView.text = tank.tankName
        holder.yearView.text = tank.tankYear!!.toString()
    }


    override fun getItemCount(): Int {
        return tanks.size
    }

    class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val flagView: ImageView = view.findViewById(R.id.flag) as ImageView
        val nameView: TextView = view.findViewById(R.id.name)
        val yearView: TextView = view.findViewById(R.id.year)

    }

    fun addItem(item: Tank) {
        this.tanks.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(item: Tank) {
        this.tanks.remove(item)
        notifyDataSetChanged()
    }

    fun changeDataset(arrayList: ArrayList<Tank>) {
        this.tanks.clear()
        this.tanks.addAll(arrayList)
        notifyDataSetChanged()
    }
}