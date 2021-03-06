package com.binarycase.saudiassociation.appUtils.deviceUtils;

import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Loma&M on 18/04/2017.
 */

public class Validation_Utils {
    //    Login username (must be at
//            least 5 characters in length,
//                    and can contain letters (A-Z),
//    numbers (0-9), @, period,
//    dash, and/or underscore)
    public static boolean isValidUsername(String userName) {
        // TODO: 10/9/2017 Adding Spaces
        return (userName.matches("^[a-zA-Z\u0621-\u0664\\s]{2,60}$"));

    }

    public static boolean isValidUserName(String userName) {
        // TODO: 10/9/2017 Adding Spaces
        //if (userName.contains()"[1-9]"))return false ;
//     boolean isValid = //userName.contains("[a-zA-Z]{2,60}$");
        boolean isValid = (userName.matches("([a-zA-Z\u0621-\u0664]+[1-9\\s]*){2,60}$"));

        return isValid;
    }

    /*A password field that consists
    at least 5 characters long and
    can contain ONLY letters
    and/or numbers.*/
    public static boolean isValidPassword(String password) {
        return password.matches("^[a-zA-Z0-9]{5,30}$");
    }

    public static boolean isValidData(String data) {
        if (data != null && !data.isEmpty() && !data.equalsIgnoreCase("null") && !data.equals("0"))
            return true;
        return false;
    }
//    public static void focus(EditText editText){
//        editText.requestFocus();
//        //MyUtils.showKeyboard(editText);
//    }


    //    validate Email
    public static boolean isValidEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    //    validate Phone
    public static boolean isValidPhone(String phone) {
        String reg = //"/^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$/";
                "([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})";
        return phone.matches(reg);
    }

    public static boolean isValidSadiPhone(String phone) {
        //String reg="/^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$/";

        Pattern sPattern
                = Pattern.compile("^(009665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$");
        return sPattern.matcher(phone).matches();
    }

    public static void focus(EditText editText) {
        editText.requestFocus();
        KeyboardUtils.showSoftInput(editText);
    }

    public static boolean isvalidString(String s) {
        return s.length() >= 2;
    }
}
