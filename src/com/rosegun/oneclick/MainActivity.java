package com.rosegun.oneclick;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.rosegun.oneclick.receiver.AdminReceiver;

public class MainActivity extends Activity {
	private DevicePolicyManager policyManager;
	private ComponentName componentName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		boolean isScreenOn = pm.isScreenOn();
		Log.i("Onclick", String.valueOf(isScreenOn));
		if (isScreenOn) {
			Log.i("Oneclick", "关闭屏幕");
			policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
			componentName = new ComponentName(this, AdminReceiver.class);
			mylock();
		} else {
			WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
					| PowerManager.ACQUIRE_CAUSES_WAKEUP , "My Tag");
			wakeLock.acquire();
			wakeLock.release();
			Log.i("Oneclick", "点亮屏幕");
		}
		this.finish();
		android.os.Process.killProcess(android.os.Process.myPid()); 
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
			//policyManager.lockNow();// 并锁屏
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
}
