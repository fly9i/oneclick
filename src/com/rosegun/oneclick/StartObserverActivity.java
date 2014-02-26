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

public class StartObserverActivity extends Activity {
	private static final String TAG = StartObserverActivity.class
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
		Intent it = this.getIntent();
		String action = it.getAction();
		Log.i(TAG, "StartObActivity is started.");
		
		AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		ComponentName mbCN = new ComponentName(this, MediaButtonReceiver.class);
		mAudioManager.registerMediaButtonEventReceiver(mbCN);
		this.finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "StartObActivity is destroyed.");
	}
}
