package com.am.codemanagement.mvp.presenter

import androidx.lifecycle.LifecycleOwner


interface IBasePresenter {
    fun onUiReady(owner: LifecycleOwner) //status update လုပ်ပေးရမယ့် function
}