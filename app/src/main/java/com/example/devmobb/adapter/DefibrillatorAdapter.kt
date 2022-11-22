package com.example.devmobb.adapter

import Defibrillator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.devmobb.R
import com.example.devmobb.ui.defibrillator.DefibrillatorMapsActivity
import com.example.devmobb.ui.defibrillator.DefibrillatorDetailActivity
import currentLocation
import defibrillatorSelected

class DefibrillatorAdapter(private val defibrillators:List<Defibrillator>, private val context: Context) : RecyclerView.Adapter<DefibrillatorAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        var name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val openingTimes : TextView = itemView.findViewById(R.id.openingTimes)
        val distance : TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_defibrillator, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val defibrillator = defibrillators[position]
        holder.name.text = defibrillator.designation
        holder.address.text = defibrillator.address

        if(defibrillator.openingDays == null || defibrillator.openingTime == null || defibrillator.closingTime == null) {
            holder.openingTimes.text = "\uD83D\uDD52Horaires inconnus..."
        } else {
            holder.openingTimes.text = "🕒${defibrillator.openingDays} | ${defibrillator.openingTime} - ${defibrillator.closingTime}"
        }

        if(currentLocation != null) {
            holder.distance.text = "${String.format("%.1f", currentLocation!!.distanceTo(defibrillator!!.toLocation())/1000)}KM"
        } else {
            holder.distance.text = "- KM"
        }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, DefibrillatorMapsActivity::class.java)
            intent.putExtra("defibrillator", defibrillator.designation)
            defibrillatorSelected = defibrillator
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return defibrillators.size
    }
}