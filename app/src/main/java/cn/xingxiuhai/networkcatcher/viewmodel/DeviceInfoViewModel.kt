package cn.xingxiuhai.networkcatcher.viewmodel

import androidx.lifecycle.ViewModel
import cn.xingxiuhai.networkcatcher.data.repository.DeviceRepository
import cn.xingxiuhai.networkcatcher.domain.usecase.GetDeviceInfoUseCase
import cn.xingxiuhai.networkcatcher.domain.model.UiDeviceInfo

class DeviceInfoViewModel : ViewModel() {
    private val deviceRepository = DeviceRepository()
    private val getDeviceInfoUseCase = GetDeviceInfoUseCase(deviceRepository)

    fun getDeviceInfo(): UiDeviceInfo {
        return getDeviceInfoUseCase.execute()
    }
}