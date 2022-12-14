package com.rahulsingh.launcherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rahulsingh.installedpackage.AppInfo

class LauncherAdapter(private val appsList: ArrayList<AppInfo>) :
    RecyclerView.Adapter<LauncherVH>() {

    override fun onBindViewHolder(viewHolder: LauncherVH, i: Int) {
        viewHolder.bindView(appsList[i])
    }

    override fun getItemCount(): Int {
        return appsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherVH {

        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.launcher_row, parent, false)
        return LauncherVH(view)
    }

    fun refreshList(newLsit: List<AppInfo>) {
        appsList.clear()
        appsList.addAll(newLsit)
        notifyDataSetChanged()
    }
}

class LauncherVH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var img: ImageView = itemView.findViewById(R.id.img) as ImageView
    private var tvAppName: TextView = itemView.findViewById(R.id.tvAppName)
    private var tvPackageName: TextView = itemView.findViewById(R.id.tvPackageName)
    private var tvVersionName: TextView = itemView.findViewById(R.id.tvVersionName)
    private var tvVersionCode: TextView = itemView.findViewById(R.id.tvVersionCode)

    private lateinit var appInfo: AppInfo

    override fun onClick(v: View) {
        val launchIntent =
            v.context.packageManager.getLaunchIntentForPackage(appInfo.packageName.toString())
        v.context.startActivity(launchIntent)
    }

    init {
        itemView.setOnClickListener(this)
    }

    fun bindView(info: AppInfo) {
        appInfo = info
        tvAppName.text = info.label
        img.setImageDrawable(info.icon)
        tvPackageName.text = info.packageName
        tvVersionName.text = info.version
        tvVersionCode.text = info.versionCode
    }
}