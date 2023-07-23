package com.am.codemanagement.viewHolder

import android.view.View
import com.am.codemanagement.R
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.delegates.MovieViewHolderDelegate
import com.am.codemanagement.utils.IMAGE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_recommended_list.view.*

class RecommendedListViewHolder(val view: View, mDelegate: MovieViewHolderDelegate) : BaseViewHolder<UpcommingMovieVO>(view) {
    private lateinit var vo : UpcommingMovieVO
    private var title = ""
    init {
        view.setOnClickListener {
            /*val upVo = UpcommingMovieVO(
                adult = vo.adult,
                backdropPath = vo.backdropPath,
                id = vo.id,
                genreIds = vo.genreIds,
                originalLanguage = vo.originalLanguage,
                originalTitle = vo.originalTitle,
                overview = vo.overview,
                popularity = vo.popularity,
                posterPath = vo.posterPath,
                releaseDate = vo.releaseDate,
                title = vo.title,
                video = vo.video,
                voteAverage = vo.voteAverage,
                voteCount = vo.voteCount
            z*/
            mDelegate.onTapMovie(vo,"up")
            /*itemView.context.startActivity(
                MovieDetailActivity.newIntent(itemView.context, vo,"up")
            )*/
        }
    }
    override fun bindData(data: UpcommingMovieVO) {
        vo = data
        Glide.with(itemView.context)
            .load( IMAGE_URL+data.posterPath)
            .error(R.drawable.welcome)
            .into(itemView.iv_image)
        view.tv_name.text = data.originalTitle
        view.tv_percent.text = "${(data.voteAverage)} % "
    }

}
