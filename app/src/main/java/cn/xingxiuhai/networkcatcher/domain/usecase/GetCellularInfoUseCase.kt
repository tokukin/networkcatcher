package cn.xingxiuhai.networkcatcher.domain.usecase

import android.content.Context
import cn.xingxiuhai.networkcatcher.data.repository.CellularRepository
import cn.xingxiuhai.networkcatcher.data.repository.DeviceRepository
import cn.xingxiuhai.networkcatcher.domain.model.UiCellularInfo



class GetCellularInfoUseCase(private val cellularRepository: CellularRepository) {
    fun execute(context: Context): UiCellularInfo {

        val cellularInfo = cellularRepository.getCellularInfo(context =  context)
        // 这里可以进行业务相关的处理，例如格式化设备型号

        val formattednetworkOperator = "运营商代码: ${cellularInfo.networkOperator}"
        val formattednetworkOperatorName = "运营商名称: ${cellularInfo.networkOperatorName}"
        val formattednetworkTypeName = "网络类型: ${cellularInfo.networkTypeName}"


        return UiCellularInfo(formattednetworkOperator = formattednetworkOperator, formattednetworkOperatorName = formattednetworkOperatorName, formattednetworkTypeName = formattednetworkTypeName)
    }
}