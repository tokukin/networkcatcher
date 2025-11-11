package cn.xingxiuhai.networkcatcher.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import cn.xingxiuhai.networkcatcher.data.model.CellularInfo
import android.util.Log



class CellularRepository {
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresPermission(anyOf = ["android.permission.READ_PHONE_STATE","android.permission.READ_BASIC_PHONE_STATE"])
    fun getCellularInfo(context: Context): CellularInfo {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // 替代 allNetworks，获取所有网络
        val networks: Array<Network> = connectivityManager.allNetworks
        networks.forEach { network ->
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities?.let {
                Log.d("CellularRepository", "Network: ${network.toString()}")

                // 判断是否为 Wi-Fi 网络
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    // 处理 Wi-Fi 逻辑
                }
                // 判断是否为蜂窝网络（4G/5G）
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    // 处理蜂窝网络逻辑
                    val telephonyManager =
                        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager



                    val networkOperator: String? = telephonyManager.networkOperator
                    // 获取运营商名称
                    val networkOperatorName: String? = telephonyManager.networkOperatorName
                    val transportInfo = capabilities.transportInfo


                    val networkType = telephonyManager.dataNetworkType
                    val networkTypeName = when (networkType) {
                        TelephonyManager.NETWORK_TYPE_UNKNOWN -> "Unknown"
                        TelephonyManager.NETWORK_TYPE_GPRS -> "GSMGPRS（2.5G）"
                        TelephonyManager.NETWORK_TYPE_EDGE-> "GSMEDGE（2.75G）"
                        TelephonyManager.NETWORK_TYPE_UMTS->"UMTS（3G）"
                        TelephonyManager.NETWORK_TYPE_HSDPA->"HSDPA（3.5G）"
                        TelephonyManager.NETWORK_TYPE_HSUPA->"HSUPA（3.5G）"
                        TelephonyManager.NETWORK_TYPE_HSPA->"HSPA（3.5G）"
                        TelephonyManager.NETWORK_TYPE_LTE->"LTE（4G）"
                        TelephonyManager.NETWORK_TYPE_HSPAP->"HSPAP（3.75G）"
                        TelephonyManager.NETWORK_TYPE_NR->"NR（5G）"
                        else -> "Unknown"
                    }
                    return CellularInfo(
                        networkOperator ?: "Unknown",
                        networkOperatorName ?: "Unknown",
                        networkTypeName?: "Unknown"
                    )
                }
                // 判断是否为以太网
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    // 处理以太网逻辑
                }
            }
        }


        return CellularInfo("Unknown", "Unknown", "Unknown")

    }
}