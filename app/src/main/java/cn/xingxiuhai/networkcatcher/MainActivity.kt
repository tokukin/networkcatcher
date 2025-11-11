package cn.xingxiuhai.networkcatcher


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import cn.xingxiuhai.networkcatcher.ui.theme.NetworkCatcherTheme
import cn.xingxiuhai.networkcatcher.ui.navigation.AppNavHost
import cn.xingxiuhai.networkcatcher.ui.navigation.NavRoutes


// 获取设备信息的方法

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkCatcherTheme {
                // 1. 创建导航控制器
                val navController = rememberNavController()
                // 2. 加载导航宿主（而非直接加载 HomeScreen）
                AppNavHost(
                    navController = navController,
                    startDestination = NavRoutes.HOME // 初始显示首页
                )
            }
        }
    }
}


