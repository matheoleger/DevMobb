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
//import com.example.devmobb.ui.defibrillator.DefibrillatorDetailActivity
import currentLocation
import defibrillatorSelected

class DefibrillatorAdapter(private val defibrillators:List<Defibrillator>, private val context: Context) : RecyclerView.Adapter<DefibrillatorAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        var name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val status : ImageView = itemView.findViewById(R.id.status)
        val openingTimes : TextView = itemView.findViewById(R.id.openingTimes)
        val distance : TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_defibrillator, parent, false)
        return ViewHolder(view)
    }

    // Pour chaque view_id on met a jour les composants de la view (cardView: CardView, name:TextView)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val defibrillator = defibrillators[position]
        holder.name.text = defibrillator.designation
        holder.address.text = defibrillator.address
        holder.openingTimes.text = "🕒${defibrillator.openingDays} ${defibrillator.openingTime} - ${defibrillator.closingTime}"

        if(currentLocation != null) {
            holder.distance.text = "${String.format("%.1f", currentLocation!!.distanceTo(defibrillator!!.toLocation())/1000)}KM"
        } else {
            holder.distance.text = "- KM"
        }

        /*if("OPEN" == defibrillator.status) {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        }*/

        /*if(station.availableBikes != null && 0 == station.availableBikes.toInt()) {
            holder.name.setTextColor(context.getColor(R.color.empty_bike))
        }*/

        holder.cardView.setOnClickListener {
            val intent = Intent(context, DefibrillatorMapsActivity::class.java)
            intent.putExtra("station", defibrillator.designation)
            defibrillatorSelected = defibrillator
            context.startActivity(intent)
        }
    }

    // On retourne le nombre d'élements dans la liste des stations
    override fun getItemCount(): Int {
        return defibrillators.size
    }
}