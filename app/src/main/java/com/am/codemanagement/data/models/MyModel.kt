package com.am.codemanagement.data.models

import androidx.lifecycle.LiveData
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Query


interface MyModel {
    fun getPopularList(
        onFailure: (String) -> Unit
    ): LiveData<List<UpcommingMovieVO>>?

    fun getUpcomingList(
        onFailure: (String) -> Unit
    ): LiveData<List<UpcommingMovieVO>>?
}