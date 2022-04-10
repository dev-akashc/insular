package com.akash.island.util

object RomVariants {

	@JvmStatic fun isMiui() = ! Hacks.SystemProperties_get.invoke("ro.miui.ui.version.name").statically().isNullOrBlank()
}