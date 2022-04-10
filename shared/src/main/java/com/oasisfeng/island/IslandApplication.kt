package com.akash.island

import android.app.Application
import com.akash.island.analytics.CrashReport

/**
 * For singleton instance purpose only.
 *
 * Created by Akash on 2018/1/3.
 */
class IslandApplication : Application() {

	companion object {
		@JvmStatic fun get() = sInstance

		lateinit var sInstance: IslandApplication
	}

	init {
		sInstance = this
		CrashReport.initCrashHandler()
	}
}