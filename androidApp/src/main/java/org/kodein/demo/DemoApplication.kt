package org.kodein.demo

import android.app.Application
import org.kodein.shared.CommonBusiness
import org.kodein.shared.PlatformContext

class DemoApplication : Application() {
    private val commonBusiness = CommonBusiness(PlatformContext(this))
    val userRepository by lazy { commonBusiness.userRepository }

    override fun onCreate() {
        super.onCreate()
        commonBusiness.userApi.fetchUsers()
    }
}