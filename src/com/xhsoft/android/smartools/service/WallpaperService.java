package com.xhsoft.android.smartools.service;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

public class WallpaperService extends Service {
	
	WallpaperManager wManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		wManager = WallpaperManager.getInstance(this);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
