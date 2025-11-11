package cn.xingxiuhai.networkcatcher.ui.screen


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cn.xingxiuhai.networkcatcher.ui.component.CommonTemplate
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.xingxiuhai.networkcatcher.domain.model.UiDeviceInfo
import cn.xingxiuhai.networkcatcher.viewmodel.DeviceInfoViewModel


// 1. 修改 Greeting 组件，接收获取设备信息的方法，而非 Activity
@Composable
fun DeviceInfoScreen(
    navController: NavHostController
) {


    Log.d("MyActivity", "This is a debug message");




    val deviceInfoViewModel: DeviceInfoViewModel = viewModel()
    val deviceInfo: UiDeviceInfo = deviceInfoViewModel.getDeviceInfo()

    CommonTemplate(navController = navController) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("设备详情")


            Text(text = deviceInfo.formattedsdkIntmodel)
            Text(text = deviceInfo.formattedsdkIntmanufacturer)
            Text(text = deviceInfo.formattedsdkIntsystemVersion)
            Text(text = deviceInfo.formattedsdkIntdevice)
            Text(text = deviceInfo.formattedsdkIntboard)
            Text(text = deviceInfo.formattedsdkIntcpuAbi)
            Text(text = deviceInfo.formattedsdkIntsdkInt)
            Text(text = deviceInfo.formattedsdkIntbuildId)
            Text(text = deviceInfo.formattedsdkIntincrementalVersion)
            Text(text = deviceInfo.formattedsdkIntbrand)



        }
    }
}

