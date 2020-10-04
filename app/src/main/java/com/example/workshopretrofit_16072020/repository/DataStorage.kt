package com.example.workshopretrofit_16072020.repository

import com.example.workshopretrofit_16072020.api.Camera
import com.example.workshopretrofit_16072020.api.MarsPicture

object DataStorage {
    fun getPicturesList(): List<MarsPicture> {
        return listOf(
            MarsPicture(
                422762,
                "http://mars.jpl.nasa.gov/msl-raw-images/msss/01035/mcam/1035ML0045330010305748E01_DXXX.jpg",
                "2015-07-05",
                1035,
                Camera("MAST")
            ),
            MarsPicture(
                552974,
                "http://mars.jpl.nasa.gov/msl-raw-images/msss/01305/mcam/1305ML0062010040405736E01_DXXX.jpg",
                "2016-04-07",
                1305,
                Camera("MAST")
            ),
            MarsPicture(
                552948,
                "http://mars.jpl.nasa.gov/msl-raw-images/msss/01305/mcam/1305ML0062040010405752E01_DXXX.jpg",
                "2016-04-07",
                1305,
                Camera("MAST")
            ),
            MarsPicture(
                440816,
                "http://mars.jpl.nasa.gov/msl-raw-images/msss/00652/mcam/0652MR0027650000401973E01_DXXX.jpg",
                "2014-06-06",
                652,
                Camera("MAST")
            ),

            MarsPicture(
                97448,
                "http://mars.jpl.nasa.gov/msl-raw-images/msss/00766/mcam/0766MR0032950040403845C00_DXXX.jpg",
                "2014-10-02",
                766,
                Camera("MAST")
            ),
            MarsPicture(
                588416,
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01476/opgs/edr/ncam/NLB_528520253EDR_S0580912NCAM00546M_.JPG",
                "2016-09-30",
                1476,
                Camera("NAVCAM")
            ),

            MarsPicture(
                580864,
                "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01442/opgs/edr/ncam/NLB_525508976EDR_S0571020NCAM00546M_.JPG",
                "2016-08-26",
                1442,
                Camera("NAVCAM")
            )
        )
    }
}