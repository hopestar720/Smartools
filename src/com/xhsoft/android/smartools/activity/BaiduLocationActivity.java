package com.xhsoft.android.smartools.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.xhsoft.android.smartools.R;
import com.xhsoft.android.smartools.service.BaiduLocationService;

public class BaiduLocationActivity extends Activity {
	
	TextView showTv;
	Button startBtn;
	Button stopBtn;
	
	BaiduLocationService.BaiduLocationBinder binder;
	
	private ServiceConnection servConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (BaiduLocationService.BaiduLocationBinder)service;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smart_location_service_activity);
		final Intent intent = new Intent();
		intent.setAction("com.xhsoft.android.smartools.service.BAIDU_LOCATION_SERVICE");
		showTv = (TextView)findViewById(R.id.smart_location_service_tv_show);
		startBtn = (Button)findViewById(R.id.smart_location_service_btn_open);
		stopBtn = (Button)findViewById(R.id.smart_location_service_btn_close);
		
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bindService(intent, servConn, Service.BIND_AUTO_CREATE);
			}
		});
		
		stopBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbindService(servConn);
			}
		});
	}

}
