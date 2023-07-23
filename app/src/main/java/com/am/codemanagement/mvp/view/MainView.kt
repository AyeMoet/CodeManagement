package com.am.codemanagement.mvp.view

import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse

interface MainView: BaseView {
    fun setUpAdapter()
    fun upcommingResponse(upcomingMovies: List<UpcommingMovieVO>)
    fun popularResponse(popularMovies: List<UpcommingMovieVO>)
    fun navigateToMovieDetailsScreen(vo: UpcommingMovieVO,from : String)
}