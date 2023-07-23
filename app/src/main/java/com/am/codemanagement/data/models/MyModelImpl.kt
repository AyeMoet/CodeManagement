package com.am.codemanagement.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.securelink.myanmarbeenchanted.data.models.BaseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MyModelImpl: BaseModel(), MyModel {
    @SuppressLint("CheckResult")
    override fun getPopularList(onFailure: (String) -> Unit): LiveData<List<UpcommingMovieVO>>? {
       mTheMovieApi.getPopularList()
           .subscribeOn(Schedulers.io())//background thread
           .observeOn(AndroidSchedulers.mainThread())
           //next event
           .subscribe({
               mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
           },
               //onError
               {
                   onFailure(it.localizedMessage ?: "")
               },
               //oncompleted
               {

               }
           )

       return mMovieDatabase?.movieDao()?.getAllMovies()
    }

    @SuppressLint("CheckResult")
    override fun getUpcomingList(onFailure: (String) -> Unit): LiveData<List<UpcommingMovieVO>>? {
        mTheMovieApi.getUpcomingList()
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            },
                //onError
                {
                    onFailure(it.localizedMessage ?: "")
                },
                //oncompleted
                {

                }
            )

        return mMovieDatabase?.movieDao()?.getAllMovies()
    }

}