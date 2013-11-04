package com.xhsoft.android.smartools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
			//取消广播
			abortBroadcast();
			//接收SMS传过来的数据
			Bundle bundle = intent.getExtras();
			if(bundle != null){
				Object[] pdus = (Object[])bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for(int i = 0 ;i<pdus.length;i++){
					messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				}
				
				for(SmsMessage message : messages){
					String addr = message.getDisplayOriginatingAddress();
					String body = message.getDisplayMessageBody();
					
					Toast.makeText(context, addr+":"+body , Toast.LENGTH_SHORT).show();
				}
			}
		}

	}

}
