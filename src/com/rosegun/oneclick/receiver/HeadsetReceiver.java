package com.rosegun.oneclick.receiver;

import com.rosegun.oneclick.MainActivity;
import com.rosegun.oneclick.R;
import com.rosegun.oneclick.StartObserverActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

public class HeadsetReceiver extends BroadcastReceiver {
	private static final String TAG = "HeadSetReceiver";
	private static HeadsetReceiver rec = new HeadsetReceiver();

	private HeadsetReceiver() {
	};

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.hasExtra("state")) {
			if (intent.getIntExtra("state", 0) == 0) {
				Toast.makeText(context, "headset not connected",
						Toast.LENGTH_LONG).show();
			} else if (intent.getIntExtra("state", 0) == 1) {
				Toast.makeText(context, "headset  connected", Toast.LENGTH_LONG)
						.show();
//				Intent it = new Intent(context, StartObserverActivity.class);
//				it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(it);
			}
		}
	}

	public static void register(Context context) {
		IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
		filter.setPriority(Integer.MAX_VALUE);
		context.registerReceiver(rec, filter);
		Log.i(TAG, "Register headset receiver.");
	}

	public static void unregister(Context context) {
		context.unregisterReceiver(rec);
		Log.i(TAG, "Unregister headset receiver.");
	}

}
