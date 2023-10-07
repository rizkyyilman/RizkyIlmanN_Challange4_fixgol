package com.catnip.rizkyilmann_challange4

import android.app.Application
import com.catnip.rizkyilmann_challange4.data.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}