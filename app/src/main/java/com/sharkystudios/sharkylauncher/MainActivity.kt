package com.sharkystudios.sharkylauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharkyLauncher()
        }
    }
}

@Composable
fun SharkyLauncher() {
    val bg = Color(0xFF090A0D)
    val accent = Color(0xFFB7F36B)

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = bg,
            primary = accent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "SHARKY LAUNCHER",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    "JAVA EDITION",
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(55.dp))

                Text(
                    "YOUR WORLD.",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    "YOUR RULES.",
                    color = accent,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Column {
                Text(
                    "Minecraft Java",
                    color = Color.White,
                    fontSize = 20.sp
                )

                Text(
                    "Version 1.21.4",
                    color = Color.Gray
                )

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accent
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        "PLAY",
                        color = Color.Black,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Text(
                "MADE BY SHARKY STUDIOS",
                color = Color.Gray,
                fontSize = 11.sp
            )
        }
    }
}
