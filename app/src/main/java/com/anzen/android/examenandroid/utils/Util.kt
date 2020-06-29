package com.anzen.android.examenandroid.utils

import android.location.Location
import com.anzen.android.examenandroid.models.Bike

/**
 * @return ArrayList<Bike>
 * @param bikes: ArrayList<Bike>
 * Remove stations with Zero Bikes
 */
fun removeBikesUnavailable(bikes: ArrayList<Bike>): ArrayList<Bike>{
    return bikes.filter { it.bikes > 0 } as ArrayList<Bike>
}
/**
 * @param bikes: ArrayList<Bike>
 * @return ArrayList<Bike>
 * Sort in descending by Bikes
 */
fun getAvailablePlaces(bikes: ArrayList<Bike>): ArrayList<Bike> {
    return bikes.sortedWith (compareBy(Bike::slots)).asReversed().toList() as ArrayList<Bike>
}
/**
 * @param bikes: ArrayList<Bike>
 * @return ArrayList<Bike>
 * Sort in descending by Places
 */
fun getAvailableBikes(bikes: ArrayList<Bike>): ArrayList<Bike> {
    return bikes.sortedWith (compareBy(Bike::bikes)).asReversed().toList() as ArrayList<Bike>
}

/**@param bikes: ArrayList<Bike>,longitude: Double,latitude: Double
 * @return  ArrayList<Bike>
 * Sort in ascending by distance to user location
 */
fun getBikesNear(bikes: ArrayList<Bike>,locationUser:Location): ArrayList<Bike> {
    for (bike in bikes) {
        bike.distance =getDistanceBetweenLocations(locationUser,bike.lat,bike.lon)
    }
    return bikes.sortedWith (compareBy(Bike::distance)).toList() as ArrayList<Bike>
}

/**
 * @param  locationUser:Location,endLatitude: Double,endLongitude: Double
 * @return Float distance
 * Get distance between User's Location and Bikes's Location
 */
fun getDistanceBetweenLocations(locationUser:Location,endLatitude: Double,endLongitude: Double): Float{
    val locationStationBike = Location("stationBikeLocation")
    locationStationBike.latitude=endLatitude
    locationStationBike.longitude=endLongitude
    return locationUser.distanceTo(locationStationBike)
}