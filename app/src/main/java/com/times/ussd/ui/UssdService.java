package com.times.ussd.ui;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by umesh on 20/11/15.
 */
public class UssdService extends AccessibilityService {

    public static String TAG = "USSDReader";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "==> " + event.getText());
        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
            return;

        String text = event.getText().toString();
        if (event.getClassName().equals("android.app.AlertDialog")) {
//            performGlobalAction(GLOBAL_ACTION_BACK);
            Log.d(TAG, "==> " + text);
            Intent intent = new Intent("com.times.ussd.action.REFRESH");
            intent.putExtra("message", text);
            sendBroadcast(intent);
        }
    }


    @Override
    public void onInterrupt() {
        UssdActivity.mIsUserRequestedTalktimeBalance = false;
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected UssdService");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        setServiceInfo(info);
    }
}