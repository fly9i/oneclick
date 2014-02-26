package com.rosegun.oneclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.rosegun.oneclick.service.OneClickService;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		// this.finish();
		// android.os.Process.killProcess(android.os.Process.myPid());
		// MediaButtonReceiver.register(this);

		// HeadsetReceiver.register(this);
		Intent it = new Intent(this, OneClickService.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		this.startService(it);

		// MediaButtonReceiver.register(this);
	}

	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// mAudioManager.unregisterMediaButtonEventReceiver(mbCN);
		// HeadsetReceiver.unregister(this);

	}
}
