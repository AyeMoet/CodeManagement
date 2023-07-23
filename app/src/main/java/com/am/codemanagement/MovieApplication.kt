package com.am.codemanagement

import android.app.Application
import com.am.codemanagement.data.models.MyModelImpl

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MyModelImpl.initDatabase(applicationContext)
    }
}