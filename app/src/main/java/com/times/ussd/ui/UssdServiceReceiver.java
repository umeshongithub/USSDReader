package com.times.ussd.ui;

/**
 * Created by umesh on 24/11/15.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.times.ussd.db.UssdDataSource;
import com.times.ussd.dto.Ussd;
import com.times.ussd.utils.Constants.UssdPatterns;
import com.times.ussd.utils.PreferenceHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UssdServiceReceiver extends BroadcastReceiver {
    public static String TAG = "USSDReader";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d(TAG, "onReceive - " + message);

        String balance = "";
        Pattern pattern = null;
        Matcher matcher = null;
        pattern = Pattern.compile(UssdPatterns.PATTERN_BALANCE);
        matcher = pattern.matcher(message);
        while (matcher.find()) {
            if (matcher.group().contains(".")) {
                balance = matcher.group();
                break;
            }
        }
        if (balance.equals(""))
            return;
        if (UssdActivity.mIsUserRequestedTalktimeBalance) {
            PreferenceHelper.getInstance(context).setValue(PreferenceHelper.PreferenceKeys.MAIN_BALANCE, balance);
            UssdActivity.mIsUserRequestedTalktimeBalance = false;
            return;
        } else if (UssdActivity.mIsUserRequestedInternetBalance) {
            PreferenceHelper.getInstance(context).setValue(PreferenceHelper.PreferenceKeys.INTERNET_BALANCE, balance);
            UssdActivity.mIsUserRequestedInternetBalance = false;
            return;
        } else {
            UssdActivity.mUpdateTalkTime = true;
        }

        //scan the ussd msg for 'call', if its a not a last call charge msg, return
        if(!message.contains("call"))
            return;

        UssdDataSource datasource = new UssdDataSource(context);
        datasource.open();

        datasource.createUssd(new Ussd(message, System.currentTimeMillis(), balance));
        datasource.close();

//        Log.d(TAG, "message-" + message + "time-" + time + "balance" + balance + "date-" + expiryDate );
//        Log.d(TAG, "onReceive - size " + datasource.getAllUssds().size());
    }

    /*@Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d(TAG, "onReceive - " + message);

//        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
//        String simOperatorName = telephonyManager.getSimOperatorName();
//            String netOperatorName = telephonyManager.getNetworkOperatorName();
//        String mobileNumber = telephonyManager.getLine1Number();
//            Log.d(TAG, "Got message:" + message);


        String balance = "", mobileNo = "", expiryDate = "";
        Pattern pattern = null;
        Matcher matcher = null;
        pattern = Pattern.compile(UssdPatterns.PATTERN_BALANCE);
        matcher = pattern.matcher(message);
        while (matcher.find()) {
            if (matcher.group().contains(".")) {
                balance = matcher.group();
                break;
            }
        }
//        if (simOperatorName.toLowerCase().contains(Operators.VADAFONE)) {
//            pattern = Pattern.compile(UssdPatterns.PATTERN_MOBILE_NO);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                mobileNo = matcher.group();
//            }
//
//            pattern = Pattern.compile(UssdPatterns.PATTERN_VADAFONE_DATE);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                expiryDate = matcher.group();
//            }
//        } else if (simOperatorName.toLowerCase().contains(Operators.AIRTEL)) {
//            pattern = Pattern.compile(UssdPatterns.PATTERN_MOBILE_NO);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                mobileNo = matcher.group();
//            }
//
//            pattern = Pattern.compile(UssdPatterns.PATTERN_AIRTEL_DATE);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                expiryDate = matcher.group();
//            }
//        } else if (simOperatorName.toLowerCase().contains(Operators.RELIANCE)) {
//            pattern = Pattern.compile(UssdPatterns.PATTERN_MOBILE_NO);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                mobileNo = matcher.group();
//            }
//
//            pattern = Pattern.compile(UssdPatterns.PATTERN_RELIANCE_DATE);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                expiryDate = matcher.group();
//            }
//        } else if (simOperatorName.toLowerCase().contains(Operators.IDEA)) {
//            pattern = Pattern.compile(UssdPatterns.PATTERN_MOBILE_NO);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                mobileNo = matcher.group();
//            }
//
//            pattern = Pattern.compile(UssdPatterns.PATTERN_RELIANCE_DATE);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                expiryDate = matcher.group();
//            }
//        } else if (simOperatorName.toLowerCase().contains(Operators.DOCOMO)) {
//            pattern = Pattern.compile(UssdPatterns.PATTERN_MOBILE_NO);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                mobileNo = matcher.group();
//            }
//
//            pattern = Pattern.compile(UssdPatterns.PATTERN_RELIANCE_DATE);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                expiryDate = matcher.group();
//            }
//        } else {
//            pattern = Pattern.compile(UssdPatterns.PATTERN_MOBILE_NO);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                mobileNo = matcher.group();
//            }
//
//            pattern = Pattern.compile(UssdPatterns.PATTERN_VADAFONE_DATE);
//            matcher = pattern.matcher(message);
//            while (matcher.find()) {
//                expiryDate = matcher.group();
//            }
//        }

//        if(UssdActivity.mIsUserRequestedTalktimeBalance) {
//                UssdActivity.mIsUserRequestedTalktimeBalance = false;
//                return;
//            }
        UssdDataSource datasource = new UssdDataSource(context);
        datasource.open();

        Ussd ussd = datasource.getLastRecord();
        if (ussd == null || !ussd.getBalance().equals(balance)) {
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date resultdate = new Date(yourmilliseconds);
            String time = sdf.format(resultdate);
//            Log.d(TAG, "date : " + time);
            datasource.createUssd(new Ussd(message, time, balance, expiryDate));
        }
        datasource.close();
        // save the new comment to the database

//        Log.d(TAG, "message-" + message + "time-" + time + "balance" + balance + "date-" + expiryDate );
//        Log.d(TAG, "onReceive - size " + datasource.getAllUssds().size());

    }*/
}
