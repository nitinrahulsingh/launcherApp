package com.rahulsingh.installedpackage

import java.util.*


interface PackageChangeListener {
    fun packageInstalled(list: ArrayList<AppInfo>)
}