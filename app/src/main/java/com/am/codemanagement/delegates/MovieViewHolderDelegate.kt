package com.am.codemanagement.delegates

import com.am.codemanagement.data.vos.UpcommingMovieVO

interface MovieViewHolderDelegate {
    fun onTapMovie(vo: UpcommingMovieVO, from: String)
}