package cn.xingxiuhai.networkcatcher.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cn.xingxiuhai.networkcatcher.ui.screen.HomeScreen
import cn.xingxiuhai.networkcatcher.ui.screen.DeviceInfoScreen
import cn.xingxiuhai.networkcatcher.ui.screen.CellularScreen
import cn.xingxiuhai.networkcatcher.ui.navigation.NavRoutes
import cn.xingxiuhai.networkcatcher.ui.screen.WifiScreen

/**
 * 导航宿主：管理所有页面的路由映射
 * @param navController 导航控制器（负责实际跳转逻辑）
 * @param startDestination 初始显示的页面路由（通常是首页）
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavRoutes.HOME
) {
    NavHost(navController = navController, startDestination = startDestination) {
        // 首页路由：绑定 HomeScreen，并传递 navController 供按钮使用
        composable(route = NavRoutes.HOME) {
            HomeScreen(navController = navController)
        }

        // 蜂窝网络页路由：绑定 CellularScreen
        composable(route = NavRoutes.CELLULAR) {
            CellularScreen(navController = navController)
        }

        // WiFi页路由：绑定 WifiScreen
        composable(route = NavRoutes.WIFI) {
            WifiScreen(navController = navController)
        }


        composable(route = NavRoutes.DEVICE_INFO) {
            DeviceInfoScreen(navController = navController)
        }
    }
}