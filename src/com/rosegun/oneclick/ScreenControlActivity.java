package com.rosegun.oneclick;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Menu;

import com.rosegun.oneclick.receiver.AdminReceiver;
import com.rosegun.oneclick.receiver.MediaButtonReceiver;

public class ScreenControlActivity extends Activity {
	private static final String TAG = ScreenControlActivity.class
			.getSimpleName();
	private DevicePolicyManager policyManager;
	private ComponentName componentName;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	
		Log.i(TAG, "ScreenControlActity is started.");


		this.finish();
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "ScreenControlActivity is destroyed.");
	}
}
