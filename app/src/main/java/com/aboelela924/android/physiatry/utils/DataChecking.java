package com.aboelela924.android.physiatry.utils;

import java.util.regex.Pattern;

public class DataChecking {
    public static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isEmpty(String s){
        return s.isEmpty();
    }

    public static boolean isLongEnough(String s, int length){
        return s.length() >= length;
    }

    public static boolean isMatch(String s1, String s2){
        return s1.equals(s2);
    }

}

