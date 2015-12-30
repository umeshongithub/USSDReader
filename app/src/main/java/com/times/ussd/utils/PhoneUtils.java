package com.times.ussd.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

/**
 * Created by umesh on 15/12/15.
 */
public class PhoneUtils {

    /**
     * Dial a ussd code
     */
    public static void dailNumber(Context context, String telNo) {
        String ussdCode = telNo + Uri.encode("#");
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
    }

    public static String getSimOperator(Context context) {
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        return telephonyManager.getSimOperatorName();
    }
}
