package cn.xingxiuhai.networkcatcher.ui.component

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cn.xingxiuhai.networkcatcher.ui.navigation.NavRoutes


/**
 * 带固定顶部菜单的通用模板
 * @param title 页面标题（中间显示的标题，可动态变化）
 * @param navController 导航控制器（用于返回按钮跳转）
 * @param content 中间内容区（动态内容）
 */
@Composable
fun CommonTemplate(
    navController: NavHostController, // 用于返回操作
    modifier: Modifier = Modifier, // 声明 modifier 参数，方便外部调整整体布局
    content: @Composable () -> Unit,

) {
    Column(modifier = Modifier.fillMaxSize()) {
        // 1. 固定顶部菜单（硬编码固定结构）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,// 子元素垂直居中
            horizontalArrangement = Arrangement.SpaceBetween// 子元素分散排列（左、中、右分开）

        ) {
            Box(
                modifier = Modifier.size(48.dp)// IconButton 默认大小为 48.dp，保持一致

            )

            // 中间：标题（可动态传入）
            Text(
                text = "网络捕手",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            // 右侧：设置按钮（固定）
            IconButton(
                onClick = { /* 固定的设置逻辑 */ },
                modifier = Modifier.size(48.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "设置"

                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 0.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        )
// 2. 顶部菜单

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            // 左侧：返回按钮（固定）
            TextButton(
                onClick = { navController.navigate(NavRoutes.CELLULAR) },
                modifier = Modifier.padding(horizontal = 4.dp),


                ) {
                Text(
                    text = "蜂窝",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            TextButton(
                onClick = { navController.navigate(NavRoutes.WIFI) },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "wifi",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            TextButton(
                onClick = { navController.navigate(NavRoutes.DEVICE_INFO) },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "设备",
                    style = MaterialTheme.typography.titleMedium
                )
            }


        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 0.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        )
        // 2. 中间内容区（动态内容）
        Box(
            modifier = Modifier
                .weight(1f) // 占满剩余空间
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            content()
        }


    }
}