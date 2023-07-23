package com.am.codemanagement.viewHolder

import android.view.View
import com.am.codemanagement.activity.MovieDetailActivity
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.delegates.MovieViewHolderDelegate
import com.am.codemanagement.utils.IMAGE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_upcomming_movie_list.view.*

class UpcomingMovieListViewHolder(val view: View, mDelegate: MovieViewHolderDelegate) : BaseViewHolder<UpcommingMovieVO>(view) {
    private lateinit var vo : UpcommingMovieVO
    private var title = ""
    init {
        view.setOnClickListener {
            mDelegate.onTapMovie(vo,"up")
        }
    }
    override fun bindData(data: UpcommingMovieVO) {
        vo = data
        Glide.with(itemView.context)
            .load( IMAGE_URL+data.posterPath)
            .into(itemView.iv_image)
        view.tv_name.text = data.originalTitle
        view.tv_description.text = data.overview
        view.tv_percent.text = "${data.voteAverage} % "
        view.tv_count.text = data.voteCount.toString()
    }

}
