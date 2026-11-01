package com.sharkystudios.sharkylauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

private val Bg = Color(0xFF090A0D)
private val Panel = Color(0xFF15171C)
private val Panel2 = Color(0xFF20232A)
private val White = Color(0xFFF4F5F7)
private val Muted = Color(0xFF9298A5)
private val Accent = Color(0xFFB7F36B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SharkyApp() }
    }
}

@Composable
fun SharkyApp() {
    var tab by remember { mutableStateOf("Home") }
    var version by remember { mutableStateOf("1.21.4") }
    var notice by remember { mutableStateOf("Minecraft runtime is not connected yet.") }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Bg, surface = Panel, primary = Accent,
        onBackground = White, onSurface = White, onPrimary = Color(0xFF10120C)
    )) {
        Scaffold(
            containerColor = Bg,
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF101116), contentColor = White) {
                    listOf("Home", "Versions", "Settings").forEach { item ->
                        val selected = tab == item
                        NavigationBarItem(
                            selected = selected,
                            onClick = { tab = item },
                            icon = {
                                Icon(
                                    when (item) {
                                        "Home" -> Icons.Default.Home
                                        "Versions" -> Icons.Default.ViewList
                                        else -> Icons.Default.Settings
                                    }, contentDescription = item
                                )
                            },
                            label = { Text(item) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Accent,
                                selectedTextColor = Accent,
                                indicatorColor = Panel2,
                                unselectedIconColor = Muted,
                                unselectedTextColor = Muted
                            )
                        )
                    }
                }
            }
        ) { padding ->
            when (tab) {
                "Home" -> HomeScreen(version, notice, onPlay = {
                    notice = "Game engine not connected. Next milestone: integrate Amethyst."
                }, onVersions = { tab = "Versions" }, padding = padding)
                "Versions" -> VersionsScreen(version, onSelect = {
                    version = it
                    notice = "Selected Minecraft $it"
                }, padding = padding)
                else -> SettingsScreen(padding)
            }
        }
    }
}

@Composable
fun Header() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier.size(46.dp).background(Panel2, RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) { Icon(Icons.Default.SportsEsports, null, tint = Accent, modifier = Modifier.size(25.dp)) }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text("SHARKY", fontSize = 19.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
            Text("LAUNCHER  •  JAVA EDITION", fontSize = 9.sp, color = Muted, letterSpacing = 1.3.sp)
        }
        Surface(color = Panel, shape = RoundedCornerShape(50)) {
            Text("v0.1.0", color = Muted, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp))
        }
    }
}

@Composable
fun HomeScreen(version: String, notice: String, onPlay: () -> Unit, onVersions: () -> Unit, padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item { Header() }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 12.dp)) {
                Text("YOUR WORLD.", fontSize = 30.sp, fontWeight = FontWeight.Black, letterSpacing = (-1).sp)
                Text("YOUR RULES.", fontSize = 30.sp, fontWeight = FontWeight.Black, color = Accent, letterSpacing = (-1).sp)
                Text("Minecraft Java, made for Android.", color = Muted, fontSize = 14.sp)
            }
        }
        item {
            Column(
                Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(22.dp)).padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("SELECTED VERSION", color = Muted, fontSize = 10.sp, letterSpacing = 1.2.sp)
                        Text(version, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Surface(color = Color(0xFF29351E), shape = RoundedCornerShape(50)) {
                        Text("NOT INSTALLED", color = Accent, fontSize = 9.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp))
                    }
                }
                Button(
                    onClick = onPlay,
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Accent, contentColor = Color(0xFF11140C))
                ) {
                    Icon(Icons.Default.PlayArrow, null)
                    Spacer(Modifier.width(8.dp))
                    Text("PLAY", fontWeight = FontWeight.Black, letterSpacing = 1.5.sp)
                }
                OutlinedButton(
                    onClick = onVersions,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = White)
                ) { Text("CHANGE VERSION") }
            }
        }
        item {
            Column(Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(18.dp)).padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("LAUNCH STATUS", color = Muted, fontSize = 10.sp, letterSpacing = 1.2.sp)
                Text(notice, fontSize = 13.sp)
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                InfoTile("DEVICE", "Android", Modifier.weight(1f))
                InfoTile("EDITION", "Java", Modifier.weight(1f))
                InfoTile("BUILD", "Preview", Modifier.weight(1f))
            }
        }
        item { Text("Made by Sharky Studios", color = Muted, fontSize = 11.sp, modifier = Modifier.fillMaxWidth(), textAlign = androidx.compose.ui.text.style.TextAlign.Center) }
    }
}

@Composable
fun InfoTile(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(15.dp)).padding(12.dp)) {
        Text(label, color = Muted, fontSize = 9.sp, letterSpacing = 1.sp)
        Spacer(Modifier.height(5.dp))
        Text(value, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
    }
}

@Composable
fun VersionsScreen(selected: String, onSelect: (String) -> Unit, padding: PaddingValues) {
    val versions = listOf("1.21.4", "1.21.1", "1.20.4", "1.19.4", "1.18.2", "1.16.5", "1.12.2", "1.8.9")
    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
        Header()
        Spacer(Modifier.height(25.dp))
        Text("VERSIONS", fontSize = 27.sp, fontWeight = FontWeight.Black)
        Text("Choose the Minecraft version you want to prepare.", color = Muted, fontSize = 13.sp)
        Spacer(Modifier.height(18.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(9.dp)) {
            items(versions) { v ->
                Row(
                    Modifier.fillMaxWidth().background(if (v == selected) Color(0xFF252D1D) else Panel, RoundedCornerShape(14.dp))
                        .clickable { onSelect(v) }.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Extension, null, tint = if (v == selected) Accent else Muted)
                    Spacer(Modifier.width(12.dp))
                    Text("Minecraft $v", Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
                    if (v == selected) Icon(Icons.Default.CheckCircle, null, tint = Accent)
                    else Text("SELECT", color = Muted, fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(padding: PaddingValues) {
    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Header()
        Spacer(Modifier.height(8.dp))
        Text("SETTINGS", fontSize = 27.sp, fontWeight = FontWeight.Black)
        SettingRow("Memory allocation", "Automatic (recommended)")
        SettingRow("Renderer", "Auto-detect")
        SettingRow("Controls", "Touch controls")
        SettingRow("Game directory", "To be configured")
        SettingRow("About", "Sharky Launcher 0.1.0")
        Text("Performance options will become active after the game engine is integrated.", color = Muted, fontSize = 12.sp)
    }
}

@Composable
fun SettingRow(title: String, value: String) {
    Column(Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(15.dp)).padding(16.dp)) {
        Text(title, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(4.dp))
        Text(value, color = Muted, fontSize = 12.sp)
    }
}
