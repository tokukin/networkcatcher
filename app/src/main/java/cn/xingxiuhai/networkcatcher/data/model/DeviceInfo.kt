package cn.xingxiuhai.networkcatcher.data.model


data class DeviceInfo(
    val model: String,
    val manufacturer: String,
    val systemVersion: String,
    val device: String,
    val board: String,
    val cpuAbi: String,
    val sdkInt: Any,
    val buildId: String,
    val incrementalVersion: String,
    val brand: String,
    )