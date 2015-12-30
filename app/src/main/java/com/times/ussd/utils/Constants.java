package com.times.ussd.utils;

/**
 * Created by umesh on 24/11/15.
 */
public class Constants {

    public static String ACCESSIBILITY_SERVICE_NAME = "com.times.ussd/com.times.ussd.ui.UssdService";
    public static String ACCESSIBILITY_SERVICE_JELLYBEAN_NAME = "com.times.ussd/com.times.ussd.ui.UssdServicePreJellyBean";

    public interface DeductionAlerts {
        String main_vadafone = "Balance for 9632717191 is Rs. 208.24. Account Exp Date 21/09/2021 23:59.\n" +
                "1.Recharge for self\n" +
                "2.Recharge or Bill Pay for others.\n" +
                "#.Main Menu, Cancel, Send";

        String main_airtel = "Your Balance is: Rs.50.42 with Validity:Jun 19 2017 Prem Ratan Dhan Payo jaise Hit HelloTune, Dial 578786.Rs85=LOCAL mobile 35p/min,28din";

        String main_idea = "Bal: 8.672Rs.Expiry: 27-08-2018\n" +
                "Reply\n" +
                "1. Rs.150-Rs.150 TT\n" +
                "2. RC58-300MB 2G for 8 days\n" +
                "3. Rs.16-Call@1.25p/2s,30D\n" +
                "4. Self Care\n" +
                "NEXT 0";

        String main_reliance = "Main Bal Rs.30.058 Val 23-11-2028,More AT *367*1#.RC200 ke RC par 200 ka TT. dial *129#";

        String main_docomo = "Bal Rs 112.96. More dial*111*1#.\n" +
                "Limited period offer RC196=40000 Sec L+S 30D";


        String deduction_vadafone = "Last Call:Duration00 Min. 07 Sec.Charge Rs0.140..Main Acc Bal Rs:338.154.Biggest Offer Call*121#";

        String deduction_reliance = "Last call charge for 00:03:07 is from Main Bal:0.100.Available Main Bal:Rs.0.125,20-12-2028.RC300 ke RC par 300 ka Talktime.*129#";

        String deduction_airtel = "Call Charge Rs. 3.08.Balance Rs.27.61.Dial 12131 to buy your best STD,local,SMS,Mobile Internet offer";
        String deduction_aircel = "";
        String deduction_idea = "";
        String deduction_docomo = "";
        String deduction_bsnl = "";

    }

    public interface UssdDataAlerts {

    }

    public interface USSDCodes {
        String TALKTIME_VADAFONE = "*111*2";
        String DATA_VADAFONE = "*111*6*2";

        String TALKTIME_AIRTEL = "*123";
        String DATA_AIRTEL = "*123*1";

        String TALKTIME_IDEA = "*121#";
        String DATA_IDEA = "*125";

        String TALKTIME_DOCOMO = "*111";
        String DATA_DOCOMO = "*111*1";

        String TALKTIME_RELIANCE = "*367";
        String DATA_RELIANCE = "*367*7";

        String TALKTIME_AIRCEL = "*125";
        String DATA_AIRCEL = "*126*2";

        String TALKTIME_BSNL = "*123";
        String DATA_BSNL = "*234";

    }

    public interface Operators {
        String VADAFONE = "vodafone";
        String AIRTEL = "airtel";
        String RELIANCE = "reliance";
        String IDEA = "idea";
        String DOCOMO = "docomo";
        String AIRCEL = "aircel";
        String BSNL = "bsnl";
    }

    public interface UssdPatterns {
        //        String PATTERN_CALL_CHARGE = "[0-9]+[.]+[0-9]+"; // Rs205.05
        String PATTERN_BALANCE = "[0-9]+[.]+[0-9]+"; // 205.05
        String PATTERN_MOBILE_NO = "[0-9]{10}";  //9676767667
        String PATTERN_VADAFONE_DATE = "[0-9]{2}[/][0-9]{2}[/][0-9]{4}[ ][0-9]{2}[:][0-9]{2}";    // 22/12/2012 12:02
        String PATTERN_AIRTEL_DATE = "[a-zA-Z]{3}[ ][0-9]{2}[ ][0-9]{4}";    // Jun 19 2017
        String PATTERN_RELIANCE_DATE = "[0-9]{2}[-][0-9]{2}[-][0-9]{4}";    // 23-12-2028
    }
}
