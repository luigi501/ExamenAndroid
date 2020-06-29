package com.anzen.android.examenandroid

import android.util.Log
import com.anzen.android.examenandroid.models.Bike
import com.anzen.android.examenandroid.utils.getAvailableBikes
import com.anzen.android.examenandroid.utils.getAvailablePlaces
import com.anzen.android.examenandroid.utils.removeBikesUnavailable
import org.junit.Test

import org.junit.Assert.*

/**
 * Testing Util
 */
class UtilUnitTest {
    @Test
    fun removeBikesUnavailableTest() {

        val bikes=ArrayList<Bike>()
        val bike01 = Bike(1,
            "CUA",
            -99.1678091,
            19.4335714,
            21, 5,
            0,"6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        val bike02 = Bike(1,
            "CUA",
            -99.1678091,
            19.4335714,
            0,
            5,
            0,"6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        bikes.add(bike01)
        bikes.add(bike02)
        assertEquals(1, removeBikesUnavailable(bikes).size)

    }


    @Test
    fun getAvailableBikesTest(){
        val bikes=ArrayList<Bike>()
        var bikesSorted=ArrayList<Bike>()
        var bikesSortedExpected=ArrayList<Bike>()
        val bike01 = Bike(1,
            "CUA",
            -99.1678091,
            19.4335714,
            21,
            5,
            0,
            "6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        val bike02 = Bike(2,
            "CUA",
            -99.1678091,
            19.4335714,
            8,
            5,
            0,"6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        val bike03 = Bike(3,
            "CUA",
            -99.1678091,
            19.4335714,
            40,
            5,
            0,"6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        bikes.add(bike01)
        bikes.add(bike02)
        bikes.add(bike03)

        bikesSortedExpected.add(bike03)
        bikesSortedExpected.add(bike01)
        bikesSortedExpected.add(bike02)

        bikesSorted =getAvailableBikes(bikes)

        assertTrue(bikesSorted[0].id == bikesSortedExpected[0].id)

    }

    @Test
    fun getAvailablePlacesTest(){
        val bikes=ArrayList<Bike>()
        var bikesSorted=ArrayList<Bike>()
        var bikesSortedExpected=ArrayList<Bike>()
        val bike01 = Bike(1,
            "CUA",
            -99.1678091,
            19.4335714,
            21,
            3,
            0,
            "6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        val bike02 = Bike(2,
            "CUA",
            -99.1678091,
            19.4335714,
            8,
            5,
            0,"6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        val bike03 = Bike(3,
            "CUA",
            -99.1678091,
            19.4335714,
            40,
            2,
            0,"6500",
            "001 - R\u00edo Sena-R\u00edo Balsas",
            "SN",
            "3,8,85",
            "OPN",
            "1 RIO SENA-RIO BALSAS",
            0.0F
        )
        bikes.add(bike02)
        bikes.add(bike01)
        bikes.add(bike03)

        bikesSortedExpected.add(bike02)
        bikesSortedExpected.add(bike01)
        bikesSortedExpected.add(bike03)

        bikesSorted =getAvailablePlaces(bikes)

        assertTrue(bikesSorted[0].id == bikesSortedExpected[0].id)

    }
}
