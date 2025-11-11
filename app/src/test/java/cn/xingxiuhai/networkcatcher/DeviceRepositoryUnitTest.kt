package cn.xingxiuhai.networkcatcher

import cn.xingxiuhai.networkcatcher.data.repository.DeviceRepository
import org.junit.Test
import org.junit.Assert.assertFalse

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class DeviceRepositoryUnitTest {
    @Test
    fun testGetDeviceInfo() {
        val deviceRepository = DeviceRepository()
        val deviceInfo = deviceRepository.getDeviceInfo()
        println("Device Model: ${deviceInfo.model}")
        println("System Version: ${deviceInfo.systemVersion}")
        assertFalse(deviceInfo.model.isEmpty())
        assertFalse(deviceInfo.systemVersion.isEmpty())
    }
}