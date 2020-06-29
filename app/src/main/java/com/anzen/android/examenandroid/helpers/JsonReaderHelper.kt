package com.anzen.android.examenandroid.helpers

import android.content.Context
import android.location.Location
import android.util.Log
import com.anzen.android.examenandroid.R
import com.anzen.android.examenandroid.models.Bike
import com.anzen.android.examenandroid.utils.*
import com.google.gson.Gson


class JsonReaderHelper(private val context: Context) {


    fun getInfoBikes(responseListener: ResponseListener<ArrayList<Bike>>, filter:Int,location:Location ) {
        try {
            val jsonBikes = context.resources.openRawResource(R.raw.bikes)
                .bufferedReader().use { it.readText() }

            val bikes =  Gson().fromJson(jsonBikes , Array<Bike>::class.java).toList()as ArrayList<Bike>
            val bikesAvailable =removeBikesUnavailable(bikes)
            when (filter) {
                0 -> {
                    responseListener.onSuccess(/* TODO RESPUESTA EN MODELO PARSEADO */ getBikesNear(bikesAvailable,location))
                }
                1 -> {
                    responseListener.onSuccess(/* TODO RESPUESTA EN MODELO PARSEADO */
                        getAvailableBikes(bikesAvailable))
                }
                else -> {
                    responseListener.onSuccess(/* TODO RESPUESTA EN MODELO PARSEADO */  getAvailablePlaces(bikesAvailable))
                }
            }
        }catch (e: Exception){
            Log.e("Error",e.toString())
            responseListener.onError(/* TODO RESPUESTA EN CASO DE ERROR AL PARSEAR */ e.toString())
        }

    }


}