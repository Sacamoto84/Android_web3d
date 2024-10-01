package com.example.web3d

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.web3d.ui.theme.Web3dTheme
import com.orhanobut.hawk.Hawk

data class AddrItem(val name: String, val ip: String, val id: Int)

var screen by mutableIntStateOf(0)


const val addressMainsail = "http://192.168.0.110:81"
const val addressFluidd = "http://192.168.0.110:80"

var addrList = listOf(
    AddrItem("Dashboard", addressMainsail, R.drawable.mainsail),
    AddrItem("Console", "$addressMainsail/console", R.drawable.mainsail_console),
    AddrItem("Files  ", "$addressMainsail/files", R.drawable.mainsail_files),
    AddrItem("Viewer", "$addressMainsail/viewer", R.drawable.mainsail_viewer),
    //AddrItem("Histoty", "$addressMainsail/history", R.drawable.mainsail_history),
    AddrItem("Config  ", "$addressMainsail/config", R.drawable.mainsail_config),

    AddrItem("Fluidd  ", addressFluidd, R.drawable.fluidd),
    AddrItem("Console  ", "$addressFluidd/#/console", R.drawable.fluidd_console),
    AddrItem("Preview  ", "$addressFluidd/#/preview", R.drawable.fluidd_preview),
    AddrItem("Задание  ", "$addressFluidd/#/jobs", R.drawable.fluidd_job),
    AddrItem("Mainsail", "http://192.168.0.111:7136", R.drawable.pretty),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        Hawk.init(this).build()

        setContent {
            Web3dTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar() }) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        web(addrList[0].ip)
                    }
                }
            }
        }
    }
}




