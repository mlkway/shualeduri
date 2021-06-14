package com.raywenderlich.login_activit.app

import android.app.Application
import com.raywenderlich.login_activit.storage.DataInit

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        DataInit.initialize(this)

    }

}