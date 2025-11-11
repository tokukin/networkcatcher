package cn.xingxiuhai.networkcatcher.ui.screen

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cn.xingxiuhai.networkcatcher.ui.component.CommonTemplate
import cn.xingxiuhai.networkcatcher.viewmodel.CellularInfoViewModel

@Composable
fun CellularScreen(navController: NavHostController) {
    val context = LocalContext.current
    // 获取当前 Activity 实例（关键修复）
    val activity = context as? Activity // 安全转换为 Activity

    val cellularInfoViewModel: CellularInfoViewModel = viewModel()

    // 1. 权限状态管理
    var isPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // 2. 注册权限请求 Launcher
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        isPermissionGranted = granted
    }

    // 观察数据
    val cellularInfo by cellularInfoViewModel.uiCellularInfo.collectAsStateWithLifecycle()

    // 3. 权限变更时加载数据
    LaunchedEffect(isPermissionGranted) {
        if (isPermissionGranted) {
            cellularInfoViewModel.loadCellularInfo(context)
        }
    }

    // 4. 低版本判断
    val isLowVersion = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q

    CommonTemplate(navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Text("蜂窝网络信息", modifier = Modifier.padding(bottom = 16.dp))

            when {
                isLowVersion -> {
                    Text(text = "该功能需要 Android 10（API 29）及以上版本支持")
                }
                isPermissionGranted -> {
                    val operator = cellularInfo.formattednetworkOperator.takeIf { it.isNotEmpty() } ?: "未知"
                    val operatorName = cellularInfo.formattednetworkOperatorName.takeIf { it.isNotEmpty() } ?: "未知"
                    val networkType = cellularInfo.formattednetworkTypeName.takeIf { it.isNotEmpty() } ?: "未知"

                    Text(text = "$operator")
                    Text(text = "$operatorName", modifier = Modifier.padding(vertical = 4.dp))
                    Text(text = "$networkType")
                }
                else -> {
                    Text("需要电话状态权限才能获取蜂窝网络信息", modifier = Modifier.padding(bottom = 16.dp))

                    Button(
                        onClick = {
                            requestPermissionLauncher.launch(android.Manifest.permission.READ_PHONE_STATE)
                        }
                    ) {
                        Text("申请权限")
                    }

                    // 修复：通过 Activity 实例调用 shouldShowRequestPermissionRationale
                    val showSettingsButton = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                            activity != null && // 确保 Activity 不为空
                            !activity.shouldShowRequestPermissionRationale(android.Manifest.permission.READ_PHONE_STATE)

                    if (showSettingsButton) {
                        Button(
                            onClick = {
                                val intent = Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.parse("package:${context.packageName}")
                                )
                                context.startActivity(intent)
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("前往设置开启权限")
                        }
                    }
                }
            }
        }
    }
}