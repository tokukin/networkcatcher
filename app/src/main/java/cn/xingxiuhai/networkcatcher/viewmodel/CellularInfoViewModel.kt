package cn.xingxiuhai.networkcatcher.viewmodel

import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.xingxiuhai.networkcatcher.data.repository.CellularRepository
import cn.xingxiuhai.networkcatcher.domain.model.UiCellularInfo
import cn.xingxiuhai.networkcatcher.domain.usecase.GetCellularInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CellularInfoViewModel : ViewModel() {
    private val cellularRepository = CellularRepository()
    private val getCellularInfoUseCase = GetCellularInfoUseCase(cellularRepository)

    // 用 StateFlow 存储数据状态，供 Compose 观察
    private val _uiCellularInfo = MutableStateFlow(UiCellularInfo())
    val uiCellularInfo: StateFlow<UiCellularInfo> = _uiCellularInfo.asStateFlow()

    // 新增：权限授予后主动加载数据的方法
    fun loadCellularInfo(context: Context) {
        viewModelScope.launch {
            // 处理 Android 版本兼容（因 CellularRepository.getCellularInfo 要求 API 29+）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                try {
                    // 调用用例获取数据，并更新 StateFlow
                    val result = getCellularInfoUseCase.execute(context = context)
                    _uiCellularInfo.value = result
                } catch (e: Exception) {
                    // 处理异常（如权限未授予时的崩溃，虽然权限已在界面层检查，但做双重保障）
                    _uiCellularInfo.value = UiCellularInfo(
                        formattednetworkOperator = "获取失败",
                        formattednetworkOperatorName = e.message ?: "未知错误",
                        formattednetworkTypeName = ""
                    )
                }
            } else {
                // 低版本设备提示不支持
                _uiCellularInfo.value = UiCellularInfo(
                    formattednetworkOperator = "不支持",
                    formattednetworkOperatorName = "需要 Android 10（API 29）及以上",
                    formattednetworkTypeName = ""
                )
            }
        }
    }
}