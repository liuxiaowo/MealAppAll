package com.mm.mealapp.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * ҡһҡ������
 * ʹ��˵����
 * private ShakeUtils mShakeUtils = null;
 * 1������Ҫʹ��ҡһҡ���ܵ�Activityʵ�����ù����ಢ����ҡһҡ������
 * mShakeUtils = new ShakeUtils( this );
 * mShakeUtils.setOnShakeListener(new OnShakeListener{
 * 		public void onShake(){
 * 			// �˴�Ϊҡһҡ������Ĳ���
 * 		}
 * });
 * 
 * 2���ֱ���Activity��onResume��onPause�����е��øù������onResume��onPause������
 * mShakeUtils.onResume();
 * mShakeUtils.onPause();
 * */
public class ShakeUtils implements SensorEventListener {
	public ShakeUtils( Context context ){
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);  
	}
	
	public void setOnShakeListener( OnShakeListener onShakeListener ){
		mOnShakeListener = onShakeListener;
	}
	
	public void onResume(){
		mSensorManager.registerListener(this,  
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),  
                SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void onPause(){
		mSensorManager.unregisterListener(this); 
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		int sensorType = event.sensor.getType();  
        //values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��    
        float[] values = event.values;  
        if (sensorType == Sensor.TYPE_ACCELEROMETER){  
            //������Ե���ҡһҡ��������
            if ((Math.abs(values[0]) > SENSOR_VALUE || Math.abs(values[1]) > SENSOR_VALUE || Math.abs(values[2]) > SENSOR_VALUE)){
            	System.out.println("sensor value == " + " " + values[ 0 ] + " " + values[ 1 ] + " " +  values[ 2 ] );
                if( null != mOnShakeListener ){
                	mOnShakeListener.onShake( );
                }
            }  
        }  
	}
	
	public interface OnShakeListener{
		public void onShake();
	}
	
	private SensorManager mSensorManager = null;
	private OnShakeListener mOnShakeListener = null;
	private static final int SENSOR_VALUE = 14;
}
