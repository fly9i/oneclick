package com.rosegun.oneclick.receiver;

import com.rosegun.oneclick.service.OneClickService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.KeyEvent;

public class MediaButtonReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
//		Log.i(TAG, "receive action:" + intent.getAction());
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

	

	}
}