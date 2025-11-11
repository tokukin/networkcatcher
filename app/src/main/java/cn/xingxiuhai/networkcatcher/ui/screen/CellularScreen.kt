package cn.xingxiuhai.networkcatcher.ui.screen


import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.core.content.ContextCompat

import androidx.navigation.NavHostController
import cn.xingxiuhai.networkcatcher.data.model.CellularInfo

import cn.xingxiuhai.networkcatcher.ui.component.CommonTemplate

import cn.xingxiuhai.networkcatcher.data.repository.CellularRepository


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts


import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.xingxiuhai.networkcatcher.domain.model.UiCellularInfo
import cn.xingxiuhai.networkcatcher.domain.model.UiDeviceInfo
import cn.xingxiuhai.networkcatcher.viewmodel.CellularInfoViewModel
import cn.xingxiuhai.networkcatcher.viewmodel.DeviceInfoViewModel


// 1. 修改 Greeting 组件，接收获取设备信息的方法，而非 Activity
@Composable
fun CellularScreen(navController: NavHostController) {
    val context = LocalContext.current
    // 权限状态：是否已授予
    var isPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    // 注册权限请求 launcher
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        isPermissionGranted = granted
        // 处理权限结果
        if (granted) {
            performActionWithPhonePermission()
        } else {
            showPermissionDeniedMessage()
        }
    }
    // 初始检查：如果未授权，可在界面加载时申请（或用户点击按钮时申请）
    LaunchedEffect(Unit) {
        if (!isPermissionGranted) {
            // 此处可选择延迟申请，或在用户触发操作时申请（推荐后者）
             requestPermissionLauncher.launch(android.Manifest.permission.READ_PHONE_STATE)
        }
    }

    val cellularInfoViewModel: CellularInfoViewModel = viewModel()
    val cellularInfo: UiCellularInfo = cellularInfoViewModel.getCellularInfo(LocalContext.current)


    CommonTemplate(navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Text("蜂窝")
            Text(text = cellularInfo.formattednetworkOperator)
            Text(text = cellularInfo.formattednetworkOperatorName)
            Text(text = cellularInfo.formattednetworkTypeName)
        }
    }



}

fun showPermissionDeniedMessage() {
    TODO("Not yet implemented")
}

fun performActionWithPhonePermission() {

}


