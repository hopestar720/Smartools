package com.xhsoft.android.smartools.activity;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.xhsoft.android.common.utils.FlashLightUtil;
import com.xhsoft.android.smartools.R;

public class FlashLightActivity extends Activity {
	
	Button btnOpen;
	Button btnClose;
	
	Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smart_flash_light_activity);
		
		vibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
		
		btnOpen = (Button)findViewById(R.id.smart_flash_ligth_btn_open);
		btnOpen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vibrator.vibrate(25);
				FlashLightUtil.getInstance().open();
			}
		});
		
		btnClose = (Button)findViewById(R.id.smart_flash_ligth_btn_close);
		btnClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vibrator.vibrate(25);
				FlashLightUtil.getInstance().close();
			}
		});
	}
	
	@Override
	protected void onPause() {
		FlashLightUtil.getInstance().close();
		FlashLightUtil.getInstance().release();
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
