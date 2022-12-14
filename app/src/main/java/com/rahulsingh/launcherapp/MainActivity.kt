package com.rahulsingh.launcherapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulsingh.installedpackage.PackageChangeListener
import com.rahulsingh.installedpackage.AppInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PackageChangeListener {
    private lateinit var viewModel: AppLauncherVm
    private lateinit var adapter: LauncherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = AppLauncherVm(application)
        rvAppList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = LauncherAdapter(viewModel.getInstalledApplicationList())
        rvAppList.adapter = adapter
        viewModel.registerPackageChangeListener(this)

        btnSensorData.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SensorDataActivity::class.java
                )
            )
        }
    }

    override fun onBackPressed() {
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unRegisterPackageChangeListener()
    }

    override fun packageInstalled(list: ArrayList<AppInfo>) {
        rvAppList.adapter = LauncherAdapter(list)
    }
}