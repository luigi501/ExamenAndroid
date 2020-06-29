package com.anzen.android.examenandroid.view

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.anzen.android.examenandroid.R
import com.anzen.android.examenandroid.adapters.BikersAdapter
import com.anzen.android.examenandroid.helpers.JsonReaderHelper
import com.anzen.android.examenandroid.models.Bike
import com.anzen.android.examenandroid.utils.ResponseListener
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*


private const val PERMISSIONS_REQUEST_LOCATION = 100

class MainActivity : AppCompatActivity() {
    var locationUser = Location("UserLocation")
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(checkLocationPermission()){
            getLocation()
        }
        val filterArray = resources.getStringArray(R.array.filter_bikes_array)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, filterArray)
        val jsonReader  =JsonReaderHelper(this)
        sp_filter.adapter=adapter
        sp_filter?.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var bikes: ArrayList<Bike>
                jsonReader.getInfoBikes(object : ResponseListener<ArrayList<Bike>> {
                    override fun onSuccess(responseObject: ArrayList<Bike>){
                        Log.i("On Success",responseObject.toString())
                        bikes =responseObject;
                        rv_bikes_list.layoutManager = LinearLayoutManager(applicationContext)
                        rv_bikes_list.adapter =  BikersAdapter(bikes)
                    }
                    override fun onError(error: String){
                        Log.e("On Error",error)
                        Toast.makeText(applicationContext,"Sucedió un error, contacte al administrador.",Toast.LENGTH_LONG).show()
                    }

                },position,locationUser)
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.title_location_permission))
                    .setMessage(getString(R.string.message_location_permission))
                    .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                        ActivityCompat.requestPermissions(this@MainActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            PERMISSIONS_REQUEST_LOCATION)
                    })
                    .create()
                    .show()

            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }
    }

    private fun getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location!=null){
                    locationUser=location
                } else {
                    Log.e("Error: ","location is null")
                }
            }

    }

}





