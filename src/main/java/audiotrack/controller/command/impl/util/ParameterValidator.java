package audiotrack.controller.command.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterValidator {

    public static boolean isValidEmail(String mail)  {
        Pattern pattern = Pattern.compile("^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\\.)+[a-z]{2,6}$");
        Matcher matcher = pattern.matcher(mail);
        boolean isValid=true;
        if (!matcher.matches()) {
            isValid=false;
        }
        return isValid;

    }

    public static boolean isValidLogin(String login)  {
        Pattern pattern = Pattern.compile("^[A-Za-z]\\w{5,}$");
        Matcher matcher = pattern.matcher(login);
        boolean isValid=true;
        if (!matcher.matches()) {
            isValid=false;
        }
        return isValid;

    }

    public static boolean isValidPassword(String password)  {
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])^[0-9a-zA-Z]{6,}$");
        Matcher matcher = pattern.matcher(password);
        boolean isValid=true;
        if (!matcher.matches()) {
            isValid=false;
        }
        return isValid;

    }

    public static boolean isNotNulls(Object... objects)  {
        boolean isValid=true;
        for (Object ob : objects) {
            if (ob == null) {
                isValid=false;
            }
        }
        return isValid;

    }

    public static boolean isNotEmptyParameters(String... strings)  {
        boolean isValid=true;
        for (String s : strings) {
            if (s.isEmpty()) {
                isValid=false;
            }
        }
        return isValid;
    }

    public static boolean isValidIntParameter(String... data)  {
        boolean isValid=true;
        for(String i: data){
            try {
                Integer.parseInt(i);
            }catch (NumberFormatException e){
                isValid=false;
            }
        }
        return isValid;
    }


    public static boolean isvValidDoubleParameter(String... data)  {
        boolean isValid=true;
        for(String d: data){
            try {
                Double.parseDouble(d);
            }catch (NumberFormatException e){
                isValid=false;
            }
        }
        return isValid;
    }

    public static boolean isValid(String stringParametr, boolean isNotEmptyParameters) {
        boolean isValid=false;
        if (isNotEmptyParameters){
            boolean validIntParameter = ParameterValidator.isValidIntParameter(stringParametr);
            if (!validIntParameter){
                isValid=false;
            }
        }
        return isValid;
    }
}
