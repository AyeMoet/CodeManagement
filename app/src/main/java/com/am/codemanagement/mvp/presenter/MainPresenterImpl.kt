package com.am.codemanagement.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.am.codemanagement.data.models.MyModel
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.mvp.view.MainView
import com.am.codemanagement.data.models.MyModelImpl


class MainPresenterImpl: ViewModel(), MainPresenter {
    //View
    var mView: MainView? = null //as reference

    //Model
    private val mMovieModel: MyModel = MyModelImpl

    override fun initView(view: MainView) {
        mView = view
        mView?.setUpAdapter()
    }

    override fun onUiReady(owner: LifecycleOwner) {
        //PopularMovie
        mMovieModel.getPopularList(
            onFailure = {
                mView?.showErrorMessage(it)
            }
        )?.observe(owner) {
            mView?.popularResponse(it)
        }


        mMovieModel.getUpcomingList(
            onFailure = {
                mView?.showErrorMessage(it)
            }
        )?.observe(owner) {
            mView?.upcommingResponse(it)
        }


    }

    override fun onTapMovie(vo: UpcommingMovieVO,from : String) {
        mView?.navigateToMovieDetailsScreen(vo,from)
    }

}

