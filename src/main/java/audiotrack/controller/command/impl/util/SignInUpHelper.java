package audiotrack.controller.command.impl.util;


public class SignInUpHelper {

    public  static boolean checkParameters(String login, String password)  {


        boolean isValid=true;
        boolean validLogin = ParameterValidator.isValidLogin(login);
        boolean validPassword = ParameterValidator.isValidPassword(password);
        if(!validLogin || !validPassword){
            isValid=false;
        }
        return isValid;
    }
}
