package com.akash.island.provisioning;

import com.akash.android.os.Loopers;
import com.akash.island.engine.BuildConfig;
import com.akash.island.util.DevicePolicies;
import com.akash.island.util.ProfileUser;
import com.akash.island.util.Users;
import com.akash.pattern.PseudoContentProvider;
import com.akash.perf.Performances;
import com.akash.perf.Stopwatch;

/**
 * Perform incremental provision
 *
 * Created by Akash on 2017/11/21.
 */
public class AutoIncrementalProvision extends PseudoContentProvider {

	@Override public boolean onCreate() {
		final Stopwatch stopwatch = Performances.startUptimeStopwatch();
		if (Users.isParentProfile()) {
			Loopers.addIdleTask(() -> IslandProvisioning.startOwnerUserPostProvisioningIfNeeded(context()));
		} else if (new DevicePolicies(context()).isProfileOwner()) {	// False if profile is not enabled yet. (during the broadcast ACTION_PROFILE_PROVISIONING_COMPLETE)
			final Thread thread = new Thread(this::startInProfile);
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
		}
		if (BuildConfig.DEBUG) Performances.check(stopwatch, 5, "IncPro.MainThread");
		return false;
	}

	@ProfileUser private void startInProfile() {
		final Stopwatch stopwatch = Performances.startUptimeStopwatch();
		IslandProvisioning.performIncrementalProfileOwnerProvisioningIfNeeded(context());
		if (BuildConfig.DEBUG) Performances.check(stopwatch, 10, "IncPro.WorkerThread");
	}
}
