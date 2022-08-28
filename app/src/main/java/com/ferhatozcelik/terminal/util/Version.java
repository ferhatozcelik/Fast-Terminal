package com.ferhatozcelik.terminal.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.TextView;

public class Version {
	public static final String TAG = "CB/EulaActivity";

	private Version() {
	}

	public static void setVersionText(Context context, TextView textView) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			textView.setText(pi.versionName);
		} catch (PackageManager.NameNotFoundException e) {
			Log.i(TAG, "Couldn't get my own package info for some reason");
		}
	}
}
