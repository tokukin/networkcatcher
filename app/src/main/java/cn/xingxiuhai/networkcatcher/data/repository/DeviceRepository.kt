package cn.xingxiuhai.networkcatcher.data.repository

import android.os.Build
import cn.xingxiuhai.networkcatcher.data.model.DeviceInfo

class DeviceRepository {
    fun getDeviceInfo(): DeviceInfo {

        val model = Build.MODEL ?: "Unknown"
        val manufacturer = Build.MANUFACTURER ?: "Unknown"
        val systemVersion = Build.VERSION.RELEASE ?: "Unknown"
        val device = Build.DEVICE ?: "Unknown"
        val board = Build.BOARD ?: "Unknown"
        val cpuAbi = Build.SUPPORTED_ABIS.joinToString(", ") ?: "Unknown"
        val sdkInt = Build.VERSION.SDK_INT ?: "Unknown"
        val buildId = Build.ID ?: "Unknown"
        val incrementalVersion = Build.VERSION.INCREMENTAL ?: "Unknown"
        val brand = Build.BRAND ?: "Unknown"



        return DeviceInfo(
            model, manufacturer, systemVersion, device, board,
            cpuAbi as String, sdkInt, buildId, incrementalVersion, brand
        )
    }
}