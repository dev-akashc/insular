package com.akash.island

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import com.akash.island.provisioning.IslandProvisioning
import com.akash.island.util.ProfileUser

/**
 * Handles events related to managed profile.
 */
class IslandDeviceAdminReceiver : DeviceAdminReceiver() {

	@ProfileUser override fun onProfileProvisioningComplete(context: Context, intent: Intent) {
        // DevicePolicyManager.ACTION_PROVISIONING_SUCCESSFUL is used instead of this trigger on Android O+.
		if (SDK_INT < O) IslandProvisioning.onProfileProvisioningComplete(context, intent)
	}
}
