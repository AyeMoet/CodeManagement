package com.am.codemanagement.mvp.presenter

import com.am.codemanagement.delegates.MovieViewHolderDelegate
import com.am.codemanagement.mvp.view.MainView

interface MainPresenter: IBasePresenter,
    MovieViewHolderDelegate {
    fun initView(view: MainView) // Presenter မှာရှိတဲ့view reference ကိုသတ်မှတ်ပေးတာ
}
