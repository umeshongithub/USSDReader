package com.times.ussd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.times.ussd.R;
import com.times.ussd.utils.Utils;

/**
 * Created by umesh on 24/12/15.
 */
public class SettingsActivity  extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        findViewById(R.id.next).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isAccessibilityEnabled(this)) {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivityForResult(intent, 0);
                Utils.showText(this, getString(R.string.turn_on_accessibility_msg));
                break;
            default:
                break;
        }
    }
}
