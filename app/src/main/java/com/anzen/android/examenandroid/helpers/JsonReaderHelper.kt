package com.anzen.android.examenandroid.helpers

import android.content.Context
import android.location.Location
import com.anzen.android.examenandroid.R
import com.anzen.android.examenandroid.models.Bike
import com.anzen.android.examenandroid.utils.*
import com.google.gson.Gson


class JsonReaderHelper(private val context: Context) {

    /**
     * Get data from raw data and parse to Bike Objects
     */
    fun getInfoBikes(responseListener: ResponseListener<ArrayList<Bike>>, filter:Int,location:Location ) {
        try {
            val jsonBikes = context.resources.openRawResource(R.raw.bikes)
                .bufferedReader().use { it.readText() }

            val bikes =  Gson().fromJson(jsonBikes , Array<Bike>::class.java).toList()as ArrayList<Bike>
            val bikesAvailable =removeBikesUnavailable(bikes)
            when (filter) {
                0 -> {
                    responseListener.onSuccess(getBikesNear(bikesAvailable,location))
                }
                1 -> {
                    responseListener.onSuccess(getAvailableBikes(bikesAvailable))
                }
                2 -> {
                    responseListener.onSuccess(getAvailablePlaces(bikesAvailable))
                }
            }
        }catch (e: Exception){
            responseListener.onError( e.toString())
        }

    }


}