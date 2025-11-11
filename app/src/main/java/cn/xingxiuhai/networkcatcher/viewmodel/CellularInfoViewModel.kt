package cn.xingxiuhai.networkcatcher.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.xingxiuhai.networkcatcher.data.repository.CellularRepository
import cn.xingxiuhai.networkcatcher.data.repository.DeviceRepository
import cn.xingxiuhai.networkcatcher.domain.model.UiCellularInfo
import cn.xingxiuhai.networkcatcher.domain.usecase.GetCellularInfoUseCase


class CellularInfoViewModel: ViewModel() {
    private val cellularRepository = CellularRepository()
    private val getCellularInfoUseCase = GetCellularInfoUseCase(cellularRepository)

    fun getCellularInfo(context: Context): UiCellularInfo {
        return getCellularInfoUseCase.execute(context =  context)
    }


}
