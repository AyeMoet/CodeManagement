package com.am.codemanagement.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.codemanagement.R
import com.am.codemanagement.activity.MovieDetailActivity
import com.am.codemanagement.adapter.RecommendedListAdapter
import com.am.codemanagement.adapter.UpcommingMovieListAdapter
import com.am.codemanagement.data.vos.RecommendedVO
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.mvp.presenter.MainPresenter
import com.am.codemanagement.mvp.presenter.MainPresenterImpl
import com.am.codemanagement.mvp.view.MainView
import com.am.codemanagement.utils.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_pager.*
import java.util.*

class PagerFragment : Fragment(), MainView {
    //private lateinit var mPresenter: MainPresenterImpl
    //Presenter
    private lateinit var mPresenter: MainPresenter
    private lateinit var mRecommendedAdapter: RecommendedListAdapter
    private lateinit var mUpcommingMovieAdapter: UpcommingMovieListAdapter
    private lateinit var dialog: Dialog
    private var isSize = false
    companion object{
        fun newFragment(): Fragment {
            val fragment = PagerFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        mPresenter.onUiReady(owner = this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    override fun setUpLoadingDialog() {
        dialog = com.am.codemanagement.utils.setUpLoadingDialog(requireContext())
    }

    override fun checkNetwork() {
        if (isNetwork(requireContext())) {
        }
    }

    override fun listener() {

    }

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.dismiss()
    }

    override fun showErrorMessage(message: String) {
        Log.d("test_data","message  $message")
    }

    override fun setUpAdapter() {
        mRecommendedAdapter = RecommendedListAdapter(requireContext(),mPresenter)
        rv_recommended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        rv_recommended.adapter = mRecommendedAdapter

        mUpcommingMovieAdapter = UpcommingMovieListAdapter(requireContext(),mPresenter)
        rv_upcomming_movie.layoutManager = LinearLayoutManager(requireContext())
        rv_upcomming_movie.adapter = mUpcommingMovieAdapter
    }

    override fun upcommingResponse(upcomingMovies: List<UpcommingMovieVO>) {
        Log.d("test_data","upcoming movie $upcomingMovies")

        mUpcommingMovieAdapter.setData(upcomingMovies.toMutableList())
    }

    override fun popularResponse(popularMovies: List<UpcommingMovieVO>) {
        Log.d("test_data","popular movie $popularMovies")
        mRecommendedAdapter.setData(popularMovies.toMutableList())
    }

    override fun navigateToMovieDetailsScreen(vo: UpcommingMovieVO,from : String) {
        startActivity(
            MovieDetailActivity.newIntent(requireContext(), vo,from)
        )
    }

    override fun init() {

    }
}