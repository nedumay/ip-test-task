package com.example.ip_test_task

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.ip_test_task.common.copyDatabase
import com.example.ip_test_task.presentation.screens.ProductListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        copyDatabase(this)
        enableEdgeToEdge()
        setContent {
            ProductListScreen()
            getWindow().setNavigationBarColor(getResources().getColor(R.color.light_blue))
        }
    }
}
