package com.hsnozan.basemvvm.api

import com.hsnozan.basemvvm.model.LoginResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {

    @GET("/3/movie/550")
    fun sendLogin(@Query("api_key") authHeader: String): Observable<LoginResponseModel>

}