package com.times.ussd.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.times.ussd.R;
import com.times.ussd.utils.Constants.Operators;
import com.times.ussd.utils.Constants.USSDCodes;
import com.times.ussd.utils.DateUtils;
import com.times.ussd.utils.FragmentHelper;
import com.times.ussd.utils.PhoneUtils;
import com.times.ussd.utils.PreferenceHelper;
import com.times.ussd.utils.Utils;

public class UssdActivity extends AppCompatActivity implements View.OnClickListener {

    private String mOperator;
    public static boolean mIsUserRequestedTalktimeBalance;
    public static boolean mIsUserRequestedInternetBalance;
    public static boolean mUpdateTalkTime;
    private boolean mIsFromSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.refresh_data_bal).setOnClickListener(this);
        findViewById(R.id.refresh_talktime_bal).setOnClickListener(this);
        findViewById(R.id.last_updated_data).setOnClickListener(this);
        findViewById(R.id.last_updated_talktime).setOnClickListener(this);

        FragmentHelper.replaceFragment(this, new DeductionsFragment(), R.id.balance_container);
        FragmentHelper.replaceFragment(this, new DeductionsFragment(), R.id.data_container);

//        Log.d("Debug", "date - " + DateUtils.getFormattedTime(System.currentTimeMillis()));

        mOperator = PhoneUtils.getSimOperator(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Utils.isAccessibilityEnabled(this)) {
            if (!mIsFromSettings) {
                startActivity(new Intent(this, SettingsActivity.class));
                mIsFromSettings = true;
                return;
            } else {
                finish();
            }
        }

        String talkTimeBalance = PreferenceHelper.getInstance(this).getValue(PreferenceHelper.PreferenceKeys.MAIN_BALANCE);
        if (!TextUtils.isEmpty(talkTimeBalance)) {
            ((TextView) findViewById(R.id.talktime_bal)).setText("Rs " + talkTimeBalance);
            findViewById(R.id.talktime_bal).setVisibility(View.VISIBLE);
            findViewById(R.id.refresh_talktime_bal).setVisibility(View.INVISIBLE);
            if (mUpdateTalkTime) {
                mUpdateTalkTime = false;
                ((TextView) findViewById(R.id.last_updated_talktime)).setText(getString(R.string.updated));
            }
        }

        String dataBalance = PreferenceHelper.getInstance(this).getValue(PreferenceHelper.PreferenceKeys.INTERNET_BALANCE);
        if (!TextUtils.isEmpty(dataBalance)) {
            ((TextView) findViewById(R.id.data_bal)).setText(dataBalance + " MB");
            findViewById(R.id.data_bal).setVisibility(View.VISIBLE);
            findViewById(R.id.refresh_data_bal).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh_talktime_bal:
            case R.id.last_updated_talktime:
                mUpdateTalkTime = true;
                mIsUserRequestedTalktimeBalance = true;
                PhoneUtils.dailNumber(this, getTalktimeBalanceUssdCode(mOperator));
                break;
            case R.id.refresh_data_bal:
            case R.id.last_updated_data:
                mIsUserRequestedInternetBalance = true;
                PhoneUtils.dailNumber(this, getDataBalanceUssdCode(mOperator));
                break;
        }
    }

    /**
     * Get Talktime USSD code based on operator
     *
     * @param operator
     * @return
     */
    private String getTalktimeBalanceUssdCode(String operator) {
        switch (operator.toLowerCase()) {
            case Operators.VADAFONE:
                return USSDCodes.TALKTIME_VADAFONE;
            case Operators.AIRTEL:
                return USSDCodes.TALKTIME_AIRTEL;
            case Operators.AIRCEL:
                mIsUserRequestedTalktimeBalance = true;
                return USSDCodes.TALKTIME_AIRCEL;
            case Operators.BSNL:
                return USSDCodes.TALKTIME_BSNL;
            case Operators.DOCOMO:
                return USSDCodes.TALKTIME_DOCOMO;
            case Operators.IDEA:
                return USSDCodes.TALKTIME_IDEA;
            case Operators.RELIANCE:
                return USSDCodes.TALKTIME_RELIANCE;
            default:
                return USSDCodes.TALKTIME_VADAFONE;
        }
    }

    /**
     * Get Data USSD code based on operator
     *
     * @param operator
     * @return
     */
    private String getDataBalanceUssdCode(String operator) {
        switch (operator.toLowerCase()) {
            case Operators.VADAFONE:
                return USSDCodes.DATA_VADAFONE;
            case Operators.AIRTEL:
                return USSDCodes.DATA_AIRTEL;
            case Operators.AIRCEL:
                mIsUserRequestedTalktimeBalance = true;
                return USSDCodes.DATA_AIRCEL;
            case Operators.BSNL:
                return USSDCodes.DATA_BSNL;
            case Operators.DOCOMO:
                return USSDCodes.DATA_DOCOMO;
            case Operators.IDEA:
                return USSDCodes.DATA_IDEA;
            case Operators.RELIANCE:
                return USSDCodes.DATA_RELIANCE;
            default:
                return USSDCodes.DATA_VADAFONE;
        }
    }

}
