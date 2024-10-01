package com.example.web3d

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("InvalidColorHexValue")
val backgrounColor = Color(0xFF0FF121212)
val borderColor = Color(0xFF3D3D3D)
val backgroundButtonColor = Color(0xFF1E1E1E)

@Composable
fun RowButtonMainSail() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgrounColor), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween

    ) {

        addrList.forEachIndexed { index, it ->

            Box(modifier = Modifier
                //.padding(horizontal = 1.dp)
                .height(56.dp)
                .fillMaxWidth()
                .weight(1f)
                .border(0.5.dp, borderColor, RoundedCornerShape(0.dp))
                //.clip(RoundedCornerShape(8.dp))
                .background(backgroundButtonColor)
                .clickable {
                    screen = index
                    if (babView?.url != it.ip) {
                        babView?.loadUrl(it.ip)
                    }

                }, contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(it.id),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier.height(40.dp)
                )

            }

        }


    }


}


@Composable
fun BottomBar() {
    RowButtonMainSail()
}