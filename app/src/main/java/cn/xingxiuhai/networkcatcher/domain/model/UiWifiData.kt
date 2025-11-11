package cn.xingxiuhai.networkcatcher.domain.model

data class UiWifiData (
    val type: String, // 网络类型（如 "WIFI"、"CELLULAR"）
    val isConnected: Boolean, // 是否连接
    val signalStrength: Int // 信号强度（如 -85）
)