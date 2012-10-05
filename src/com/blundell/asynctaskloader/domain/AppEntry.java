package com.blundell.asynctaskloader.domain;

import java.io.File;

import com.blundell.asynctaskloader.ui.loader.AppListLoader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

public class AppEntry {

	private final AppListLoader loader;
	private final ApplicationInfo info;
	private final File apkFile;
	private String label;
	private Drawable icon;
	private boolean mounted;

	public AppEntry(AppListLoader loader, ApplicationInfo info) {
		this.loader = loader;
		this.info = info;
		apkFile = new File(info.sourceDir);
	}

	public ApplicationInfo getApplicationInfo(){
		return info;
	}

	public String getLabel(){
		return label;
	}

	public Drawable getIcon(){
		if(icon == null){
			if(apkFile.exists()){
				icon = info.loadIcon(loader.pm);
				return icon;
			} else {
				mounted = false;
			}
		} else if (!mounted) {
			// If the app wasn't mounted but is now mounted, reload its icon
			if(apkFile.exists()){
				mounted = true;
				icon = info.loadIcon(loader.pm);
			}
		} else {
			return icon;
		}

		return loader.getContext().getResources().getDrawable(android.R.drawable.sym_def_app_icon);
	}

	@Override
	public String toString(){
		return label;
	}

	public void loadLabel(Context context){
		if(label == null || !mounted){
			if(!apkFile.exists()){
				mounted = false;
				label = info.packageName;
			} else {
				mounted = true;
				CharSequence label = info.loadLabel(context.getPackageManager());
				this.label = label != null ? label.toString() : info.packageName;
			}
		}
	}
}
