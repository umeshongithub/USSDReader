package com.times.ussd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by umesh on 29/12/15.
 */
public class DateUtils {
    public static String getFormattedDate(long timeInMillis) {
        return android.text.format.DateUtils.getRelativeTimeSpanString(timeInMillis).toString();
    }

    public static String getFormattedTime(long timeInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        Date resultDate = new Date(timeInMillis);
        return sdf.format(resultDate);
    }
}
