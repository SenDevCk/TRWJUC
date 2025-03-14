package com.bih.nic.bsphcl.trwjuc.retrofit


/**
 *Created by Chandan Singh on 2/6/2025.
 */

import com.bih.nic.bsphcl.trwjuc.data.Circle
import com.bih.nic.bsphcl.trwjuc.data.Division
import com.bih.nic.bsphcl.trwjuc.data.JobwiseMaterialUtilizationSegment
import com.bih.nic.bsphcl.trwjuc.data.LoginRequest
import com.bih.nic.bsphcl.trwjuc.data.LoginResponse
import com.bih.nic.bsphcl.trwjuc.data.MyResponse
import com.bih.nic.bsphcl.trwjuc.data.Section
import com.bih.nic.bsphcl.trwjuc.data.Subdivision
import com.bih.nic.bsphcl.trwjuc.data.TRWRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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
    @POST("/api-trwjuc/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/api-trwjuc/joint-inspection/reportData")
    suspend fun postTRWData(@Body request: TRWRequest): Response<MyResponse<String>>

}