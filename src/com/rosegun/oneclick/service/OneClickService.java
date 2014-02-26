package com.rosegun.oneclick.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.KeyEvent;

import com.rosegun.oneclick.MainActivity;
import com.rosegun.oneclick.R;
import com.rosegun.oneclick.receiver.AdminReceiver;
import com.rosegun.oneclick.receiver.HeadsetReceiver;
import com.rosegun.oneclick.receiver.MediaButtonReceiver;

public class OneClickService extends Service {
	private static final String TAG = OneClickService.class.getSimpleName();
	private DevicePolicyManager policyManager;
	private ComponentName componentName;

	private BroadcastReceiver bdr= new  BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i(TAG, "receive action:" + intent.getAction());
			boolean isActionMediaButton = Intent.ACTION_MEDIA_BUTTON.equals(intent
					.getAction());
			if (!isActionMediaButton) {
				return;
			}
			KeyEvent event = (KeyEvent) intent
					.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if (event == null) {
				return;
			}

			boolean isActionUp = (event.getAction() == KeyEvent.ACTION_UP);
			if (!isActionUp) {
				return;
			}

			// int keyCode = event.getKeyCode();
			// long eventTime = event.getEventTime() - event.getDownTime();//
			// 按键按下到松开的时长

			OneClickService.this.controlScreen();

		}
	};
	
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		Log.i(TAG, "Start oneclickservice.");
		HeadsetReceiver.register(this);
		IntentFilter itf=new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
		itf.setPriority(Integer.MAX_VALUE);
		registerReceiver(bdr, itf);
		
		
		Log.i(TAG,"Registered media_button receiver.");

		Notification n = new Notification(R.drawable.ic_launcher, "一键监听中",
				System.currentTimeMillis());
		n.flags = Notification.FLAG_FOREGROUND_SERVICE;
		PendingIntent pit = PendingIntent.getActivity(this, 0, new Intent(this,
				MainActivity.class), Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		n.setLatestEventInfo(this, "一键", "一键监听中", pit);
		startForeground(1, n);

		AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		ComponentName mbCN = new ComponentName(this, bdr.getClass());
		mAudioManager.registerMediaButtonEventReceiver(mbCN);

		
//		HeadsetReceiver.register(this);
	}

	public void controlScreen(){
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		boolean isScreenOn = pm.isScreenOn();
		Log.i("Onclick", String.valueOf(isScreenOn));

		if (isScreenOn) {
			Log.i("Oneclick", "关闭屏幕");
			policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
			componentName = new ComponentName(this, AdminReceiver.class);
			mylock();
		} else {
			WakeLock wakeLock = pm.newWakeLock(
                    PowerManager.SCREEN_DIM_WAKE_LOCK
                    | PowerManager.ON_AFTER_RELEASE,
                    TAG);
			wakeLock.acquire(6000);
			wakeLock.release();
			Log.i("Oneclick", "点亮屏幕");
		}
	}
	
	private void activeManager() {
		// 使用隐式意图调用系统方法来激活指定的设备管理器
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
		startActivity(intent);
	}

	private void mylock() {
		boolean active = policyManager.isAdminActive(componentName);
		if (!active) {// 若无权限
			activeManager();// 去获得权限
			// policyManager.lockNow();// 并锁屏
		}
		if (active) {
			policyManager.lockNow();// 直接锁屏
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		HeadsetReceiver.unregister(this);
		unregisterReceiver(bdr);
	}
}
