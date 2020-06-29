package com.anzen.android.examenandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anzen.android.examenandroid.R
import com.anzen.android.examenandroid.models.Bike
import kotlinx.android.synthetic.main.item_bike_station.view.*

class BikersAdapter(private val bikes : ArrayList<Bike>): RecyclerView.Adapter<BikersAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind(bike: Bike){
            with(itemView){
                station_name.text = bike.name
                address_station.text = bike.address
                bikes_available.text  = context.getString(R.string.item_bikes_available,bike.bikes)
                places_available.text = context.getString(R.string.item_places_available,bike.slots)
            }

        }


    }

    /**
     * @return
     * Inflate XML items
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bike_station,parent,false)
        )
    }

    /**
     * @return
     * Numbers of elements
     */
    override fun getItemCount(): Int {
       return bikes.size
    }

    /**
     * @param
     * Bind list of bikes with viewHolder
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val bike = bikes[position]
        holder.bind(bike)
    }
}