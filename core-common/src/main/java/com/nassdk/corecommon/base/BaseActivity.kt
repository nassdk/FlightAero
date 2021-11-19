package com.nassdk.corecommon.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.modo.Modo
import com.nassdk.corecommon.extensions.appComponent
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var modo: Modo

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}