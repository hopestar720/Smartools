package com.xhsoft.android.common.utils;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class FlashLightUtil {

	private static FlashLightUtil instance = null;
	private static Camera camera = null;

	private FlashLightUtil() {
		if (camera == null) {
			camera = Camera.open();
			camera.startPreview();
		}
	}

	public static FlashLightUtil getInstance() {
		if (instance == null) {
			instance = new FlashLightUtil();
		}
		return instance;
	}

	/**
	 * 打开闪光灯
	 */
	public void open() {
		Parameters params = camera.getParameters();
		params.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(params);

	}

	/**
	 * 关闭闪光灯
	 */
	public void close() {
		Parameters params = camera.getParameters();
		params.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(params);

	}

	public void release() {
		if (camera != null) {
			camera.release();
		}
	}

}
