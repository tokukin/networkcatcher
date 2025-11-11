package cn.xingxiuhai.networkcatcher.ui.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.xingxiuhai.networkcatcher.ui.theme.NetworkCatcherTheme
import cn.xingxiuhai.networkcatcher.ui.component.CommonTemplate

// 1. 修改 Greeting 组件，接收获取设备信息的方法，而非 Activity
@Composable
fun WifiScreen(navController: NavHostController) {
    CommonTemplate(navController = navController) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text("wifi")


        }
    }
}

// 3. 预览函数中创建模拟的导航控制器
@Preview(showBackground = true)
@Composable
fun WifiScreenPreview() {
    NetworkCatcherTheme {

        val navController = rememberNavController()

        WifiScreen(navController = navController)
    }
}


