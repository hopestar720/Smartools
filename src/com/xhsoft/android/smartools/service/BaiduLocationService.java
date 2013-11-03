package com.xhsoft.android.smartools.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//�����õ�λ�����ѹ��ܣ���Ҫimport����
//���ʹ�õ���Χ�����ܣ���Ҫimport������
import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;

public class BaiduLocationService extends Service {
	
	BaiduLocationListener locationListener = new BaiduLocationListener();
	public LocationClient mLocationClient = null;
	public static String TAG = "Smartools";
	
	private BaiduLocationBinder bdlBinder = new BaiduLocationBinder();
	
	private Runnable locationTask = new Runnable() {
		
		@Override
		public void run() {
			
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return bdlBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public class BaiduLocationBinder extends Binder{
		
	}
	
	@Override
	public void onCreate() {
		mLocationClient = new LocationClient(this);
		mLocationClient.setAK("AB182e6e1e64df8cded1321c21235546");
		mLocationClient.registerLocationListener(locationListener);
		mLocationClient.setLocOption(getLocationOption());
		super.onCreate();
		Log.d(TAG, "... BaiduService onCreate... pid=" + Process.myPid());
	}
	
	//������ز���
		private LocationClientOption getLocationOption(){
			LocationClientOption option = new LocationClientOption();
			option.setOpenGps(true);
			option.setPoiExtraInfo(true);
			option.setAddrType("all");//���صĶ�λ���������ַ��Ϣ
			option.setScanSpan(5000);//���÷���λ����ļ��ʱ��Ϊ5000ms
			option.disableCache(true);//��ֹ���û��涨λ
			option.setPoiNumber(5);    //��෵��POI����   
			option.setPoiDistance(1000); //poi��ѯ����        
			option.setPoiExtraInfo(true); //�Ƿ���ҪPOI�ĵ绰�͵�ַ����ϸ��Ϣ 
			option.setCoorType(BDGeofence.COORD_TYPE_BD09LL);		//������������ ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
			option.setServiceName("com.baidu.location.service_v2.9");
			option.setPriority(LocationClientOption.NetWorkFirst);//������������
			option.setPoiNumber(10);
			return option;
		}
	
	
	public class BaiduLocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return ;
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				/**
				 * ��ʽ����ʾ��ַ��Ϣ
				 */
//				sb.append("\nʡ��");
//				sb.append(location.getProvince());
//				sb.append("\n�У�");
//				sb.append(location.getCity());
//				sb.append("\n��/�أ�");
//				sb.append(location.getDistrict());
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			}
			sb.append("\nsdk version : ");
			sb.append(mLocationClient.getVersion());
			sb.append("\nisCellChangeFlag : ");
			sb.append(location.isCellChangeFlag());
			Log.i(TAG, sb.toString());
		}

		@Override
		public void onReceivePoi(BDLocation location) {
			if (location == null){
				return ; 
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("Poi time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : "); 
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
			} 
			if(location.hasPoi()){
				sb.append("\nPoi:");
				sb.append(location.getPoi());
			}else{				
				sb.append("noPoi information");
			}
		}
		
	}

}
