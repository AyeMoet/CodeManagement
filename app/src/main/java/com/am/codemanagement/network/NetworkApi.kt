package com.am.codemanagement.network

import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse
import com.am.codemanagement.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface NetworkApi {
    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularList(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<PopularListResponse>

    @GET(upcomming_url)
    fun getUpcomingList(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<UpcommingListResponse>
}