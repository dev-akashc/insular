package com.akash.shizuku

import rikka.shizuku.ShizukuProvider

class NonRootShizukuProvider: ShizukuProvider() {

    override fun onCreate() = disableAutomaticSuiInitialization().run { super.onCreate() }
}