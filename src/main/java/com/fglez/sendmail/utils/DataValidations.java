package com.fglez.sendmail.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA
 * User: Fernando Gonzalez<fgonzalez@syesoftware.com>
 * Date: 7/21/16.
 * Time: 1:11 PM.
 */
public class DataValidations {

    public static boolean emailValidation(String email) {
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}