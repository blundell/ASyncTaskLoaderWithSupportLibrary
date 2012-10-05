package com.blundell.asynctaskloader.receiver;

import com.blundell.asynctaskloader.ui.loader.AppListLoader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Looks for changes to the installed apps so the loader can be updated
 * @author paul.blundell
 *
 */
public class PackageIntentReceiver extends BroadcastReceiver {
	final AppListLoader loader;

	public PackageIntentReceiver(AppListLoader loader) {
		this.loader = loader;
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
		filter.addDataScheme("package");
		loader.getContext().registerReceiver(this, filter);
		// Register for events related to SD Card installation
		IntentFilter sdFilter = new IntentFilter();
		sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
		sdFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
		loader.getContext().registerReceiver(this, sdFilter);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		loader.onContentChanged();
	}

}
