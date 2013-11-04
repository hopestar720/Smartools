/**
 * 
 */
package com.xhsoft.android.smartools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * @author Admin
 * 
 */
public class PowerLevelRecevier extends BroadcastReceiver {
	
	Vibrator vibrator;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		int current = bundle.getInt("level");
		int total = bundle.getInt("scale");
		
		Toast.makeText(context, current + "/" + total, Toast.LENGTH_LONG)
				.show();
	}

}
