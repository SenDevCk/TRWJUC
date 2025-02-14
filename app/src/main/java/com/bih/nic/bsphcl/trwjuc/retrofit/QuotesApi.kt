package com.bih.nic.bsphcl.trwjuc.retrofit


/**
 *Created by Chandan Singh on 2/6/2025.
 */

import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.Division
import com.bih.nic.bsphcl.trwjuc.data.JobwiseMaterialUtilizationSegment
import com.bih.nic.bsphcl.trwjuc.data.Section
import com.bih.nic.bsphcl.trwjuc.data.Subdivision
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi {
    @GET("/api-trwjuc/appData/getCircle")
    suspend fun getCircle() : Response<List<Circle>>

    @GET("/api-trwjuc/appData/getDivision")
    suspend fun getDivision() : Response<List<Division>>

    @GET("/api-trwjuc/appData/getSubDivision")
    suspend fun getSubdivision() : Response<List<Subdivision>>

    @GET("/api-trwjuc/appData/getSection")
    suspend fun getSection() : Response<List<Section>>

    @GET("/api-trwjuc/appData/getJobwiseMatUtilizationSeg")
    suspend fun getJobwiseMatUtilizationSeg() : Response<List<JobwiseMaterialUtilizationSegment>>
}