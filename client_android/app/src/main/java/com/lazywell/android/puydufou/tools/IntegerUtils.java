package com.lazywell.android.puydufou.tools;

/**
 * Created by victor on 18/06/2015.
 */
public class IntegerUtils {

    public static String to2charString(int value){
        if(value < 10)
            return "0" + value;
        else
            return String.valueOf(value);
    }
}
