package com.akash.island;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.akash.island.provisioning.IslandProvisioning;
import com.akash.island.util.Users;

/**
 * Handle {@link Intent#ACTION_MY_PACKAGE_REPLACED}
 *
 * Created by Akash on 2017/7/20.
 */
public class AppUpdateReceiver extends BroadcastReceiver {

	@Override public void onReceive(final Context context, final Intent intent) {
		// Currently, just blindly start the device owner provisioning, since it is idempotent, at least at present.
		if (Intent.ACTION_MY_PACKAGE_REPLACED.equals(intent.getAction()))
			if (Users.isParentProfile()) IslandProvisioning.startOwnerUserPostProvisioningIfNeeded(context);
	}
}
