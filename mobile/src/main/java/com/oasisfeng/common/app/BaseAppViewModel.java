package com.akash.common.app;

import static android.content.pm.ApplicationInfo.FLAG_SYSTEM;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.akash.android.ui.IconResizer;
import com.akash.androidx.lifecycle.NonNullMutableLiveData;
import com.akash.island.IslandApplication;
import com.akash.island.mobile.R;

/**
 * View-model of basic app entry
 *
 * Created by Akash on 2016/8/11.
 */
public class BaseAppViewModel extends ViewModel {

	public final AppInfo info;
	public final ObservableField<Drawable> icon = new ObservableField<>();		// Issue in data-binding - MutableLiveData causes initially empty icons.
	public transient final NonNullMutableLiveData<Boolean> selected = new NonNullMutableLiveData<>(false);
	private volatile boolean mIconLoadingStarted;

	public boolean isSystem() { return (info.flags & FLAG_SYSTEM) != 0; }

	public void onViewAttached(final View v) {
		if (mIconLoadingStarted) return;
		mIconLoadingStarted = true;
		info.loadUnbadgedIcon(sIconResizer::createIconThumbnail, icon::set);
	}

	public BaseAppViewModel(final AppInfo info) { this.info = info; }

	/* Helper functions for implementing ObservableSortedList.Sortable */

	protected boolean isSameAs(final BaseAppViewModel another) {
		return this == another || info.packageName.equals(another.info.packageName);
	}

	protected boolean isContentSameAs(final BaseAppViewModel another) {
		return TextUtils.equals(info.getLabel(), another.info.getLabel()) && info.flags == another.info.flags;
	}

	private final static IconResizer sIconResizer = new IconResizer((int) IslandApplication.get().getResources().getDimension(R.dimen.app_icon_size));	// TODO: Avoid static
}
