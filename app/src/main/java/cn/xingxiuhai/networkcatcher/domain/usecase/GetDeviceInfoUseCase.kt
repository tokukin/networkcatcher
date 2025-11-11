package cn.xingxiuhai.networkcatcher.domain.usecase

import cn.xingxiuhai.networkcatcher.data.repository.DeviceRepository
import cn.xingxiuhai.networkcatcher.domain.model.UiDeviceInfo

class GetDeviceInfoUseCase(private val deviceRepository: DeviceRepository) {
    fun execute(): UiDeviceInfo {
        val deviceInfo = deviceRepository.getDeviceInfo()
        // 这里可以进行业务相关的处理，例如格式化设备型号


        val formattedmodel = "设备型号: ${deviceInfo.model}"
        val formattedmanufacturer = "制造商: ${deviceInfo.manufacturer}"
        val formattedsystemVersion = "系统版本: ${deviceInfo.systemVersion}"
        val formatteddevice = "硬件名称: ${deviceInfo.device}"
        val formattedboard = "主板名称: ${deviceInfo.board}"
        val formattedcpuAbi = " CPU 指令集架构: ${deviceInfo.cpuAbi}"
        val formattedsdkInt = " SDK 版本号: ${deviceInfo.sdkInt}"
        val formattedbuildId = "构建版本: ${deviceInfo.buildId}"
        val formattedincrementalVersionv = "内部版本号: ${deviceInfo.incrementalVersion}"
        val formattedbrand = "设备品牌: ${deviceInfo.brand}"




        return UiDeviceInfo(formattedmodel, formattedmanufacturer, formattedsystemVersion,formatteddevice,formattedboard,
            formattedcpuAbi,formattedsdkInt,formattedbuildId,formattedincrementalVersionv,formattedbrand)
    }
}