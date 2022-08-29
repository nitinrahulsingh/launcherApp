package com.rahulsingh.launcherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rahulsingh.installedpackage.AppInfo
import com.rahulsingh.installedpackage.InstalledPackageManager
import com.rahulsingh.installedpackage.PackageChangeListener


class AppLauncherVm(application: Application) : AndroidViewModel(application) {

    private val packageManager = InstalledPackageManager(getApplication())

    fun getInstalledApplicationList(): ArrayList<AppInfo> {
        return packageManager.getInstalledApps()
    }

    fun registerPackageChangeListener(listener: PackageChangeListener) {
        packageManager.registerPackageChangeListener(listener)
    }

    fun unRegisterPackageChangeListener() {
        packageManager.unRegisterPackageChangeListener()
    }
}