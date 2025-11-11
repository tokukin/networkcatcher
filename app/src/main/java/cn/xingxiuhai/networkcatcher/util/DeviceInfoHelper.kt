package cn.xingxiuhai.networkcatcher.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat

object DeviceInfoHelper {

    // 基础设备信息（无需权限）
    fun getBasicInfo(context: Context): Map<String, String> {
        return mapOf(
            "设备型号" to Build.MODEL,
            "品牌" to Build.BRAND,
            "制造商" to Build.MANUFACTURER,
            "安卓版本" to Build.VERSION.RELEASE,
            "SDK版本" to Build.VERSION.SDK_INT.toString(),
            "主板" to Build.BOARD,
            ("CPU架构" to Build.SUPPORTED_ABIS.firstOrNull() ?: "未知") as Pair<String, String>,
            "Android ID" to getAndroidId(context)
        )
    }

    // 获取Android ID（无需权限）
    fun getAndroidId(context: Context): String {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ) ?: "获取失败"
    }

    // 获取IMEI（需READ_PHONE_STATE权限）
    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    fun getImei(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        // 检查权限
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "缺少READ_PHONE_STATE权限"
        }

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                telephonyManager.imei ?: "获取失败"
            } else {
                @Suppress("DEPRECATION")
                telephonyManager.deviceId ?: "获取失败"
            }
        } catch (e: Exception) {
            "获取失败: ${e.message}"
        }
    }

    // 获取WiFi MAC地址（需ACCESS_WIFI_STATE权限）
    fun getWifiMacAddress(context: Context): String {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_WIFI_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "缺少ACCESS_WIFI_STATE权限"
        }

        val wifiInfo: WifiInfo? = wifiManager.connectionInfo
        return wifiInfo?.macAddress ?: "获取失败"
    }

    // 获取屏幕分辨率
    fun getScreenResolution(context: Context): String {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(metrics)
        return "${metrics.widthPixels}x${metrics.heightPixels}"
    }

    // 获取应用版本号
    fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toString()
            } else {
                @Suppress("DEPRECATION")
                packageInfo.versionCode.toString()
            }
        } catch (e: Exception) {
            "获取失败: ${e.message}"
        }
    }
}
