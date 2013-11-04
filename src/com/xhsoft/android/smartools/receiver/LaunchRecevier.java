/**
 * 
 */
package com.xhsoft.android.smartools.receiver;

import com.xhsoft.android.smartools.service.SmsFilterService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Admin
 *
 */
public class LaunchRecevier extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context,SmsFilterService.class);
		context.startService(service);
	}

}
