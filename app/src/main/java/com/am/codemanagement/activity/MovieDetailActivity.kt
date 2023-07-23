package com.am.codemanagement.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.codemanagement.R
import com.am.codemanagement.adapter.CastListAdapter
import com.am.codemanagement.data.vos.*
import com.am.codemanagement.utils.IMAGE_URL
import com.am.codemanagement.utils.dateFormat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mAdapter: CastListAdapter
    private lateinit var list: ArrayList<CastVO>
    private lateinit var uplist: UpcommingMovieVO
    private lateinit var type : String

    companion object {
        const val LIST_OFFLINE = "offlineList"
        const val LIST_UP = "upList"
        const val TYPE = "type"
        fun newIntent(context: Context, vo: UpcommingMovieVO, type: String): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(LIST_UP,vo)
            intent.putExtra(TYPE,type)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        init()
        loadData()
        setUpAdapter()
        listener()
    }

    private fun init() {
        list = ArrayList()
        type = intent.getStringExtra(TYPE) as String

    }

    private fun loadData() {
        uplist = intent.getSerializableExtra(LIST_UP) as UpcommingMovieVO
        Glide.with(this)
            .load( IMAGE_URL +uplist.posterPath)
            .error(R.drawable.welcome)
            .into(iv_image)
        tv_name.text = uplist.originalTitle
        tv_percent.text = if ((uplist.genreIds?.size ?: 0) > 0) {
            "${uplist.genreIds?.get(0)} %"
        } else {
            "${uplist.voteAverage} %"
        }
        tv_year.text = "UA|${uplist.releaseDate?.let { dateFormat(it) }}"
        tv_votes.text = "${uplist.voteCount} votes"
        tv_language.text = if (uplist.originalLanguage == "en") {
            "English"
        } else if (uplist.originalLanguage == "ko") {
            "Korea"
        } else if (uplist.originalLanguage == "ja") {
            "Japan"
        } else if (uplist.originalLanguage == "fr") {
            "France"
        } else {
            "El Salvador"
        }
        tv_description.text = uplist.overview

    }

    private fun setUpAdapter() {
        mAdapter = CastListAdapter(this)
        rv_cast_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_cast_list.adapter = mAdapter
        list.add(CastVO("Josh Gab",""))
        list.add(CastVO("Josh Gab",""))
        list.add(CastVO("Josh Gab",""))
        mAdapter.setData(list)
    }

    private fun listener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }

        iv_fav.setOnClickListener {
            if (type != "up") {
                uplist.isFavourite = true
                Glide.with(this)
                    .load(R.drawable.ic_baseline_favorite_border_24)
                    .into(iv_fav)
            }
        }
    }
}