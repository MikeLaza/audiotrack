package audiotrack.controller.command.impl;

import audiotrack.bean.UserEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.*;
import audiotrack.service.UserService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static audiotrack.controller.PageEnum.UNIVERSAL_PAGE;

public class SignUpCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(SignUpCommand.class);

    public static final Double BALANCE_DEFAULT =20d;
    public static final Integer DISCOUNT_DEFAULT =0;
    public static final String AFTER_REGISTRATION="audiotrack?command=after_registration";
    public static final String TRUE="true";


    public void execute(HttpServletRequest req, HttpServletResponse resp)  {

        String login=req.getParameter(ParameterEnum.LOGIN.toString());
        Locale locale = req.getLocale();
        String localeStr = locale.toString();

        signUpClient(login,localeStr,req);

        prepareSessionAttributes(req, resp, login, localeStr);
        ForwardRedirectHelper.provideRedirect(resp,AFTER_REGISTRATION);

    }

    private void prepareSessionAttributes(HttpServletRequest req, HttpServletResponse resp, String login, String localeStr) {
        HttpSession session=req.getSession(true);
        session.setAttribute(ParameterEnum.LOGIN.toString(), login);
        session.setAttribute(ParameterEnum.ROLE.toString(), ParameterEnum.CLIENT.toString());
        Cookie cookie = new Cookie(ParameterEnum.LOGIN.toString(), login);
        resp.addCookie(cookie);
        session.setAttribute(ParameterEnum.USER_LOCAL.toString(), localeStr);
        session.setAttribute(ParameterEnum.PAGE.toString(), UNIVERSAL_PAGE);
    }


    public void signUpClient(String login,String localeStr,HttpServletRequest req){

        String password=req.getParameter(ParameterEnum.PASSWORD.toString());
        String email= req.getParameter(ParameterEnum.EMAIL.toString());
        String isAdmin= req.getParameter(ParameterEnum.IS_ADMIN.toString());

        UserEnum userEnum=UserEnum.CLIENT;
        String trueStr = TRUE;
        if(trueStr.equals(isAdmin)){
            userEnum=UserEnum.ADMIN;
        }
        try {

            boolean isValidParameters= SignInUpHelper.checkParameters(login,password);
            boolean isValidEmail = ParameterValidator.isValidEmail(email);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService UserService = serviceFactory.getUserService();
            if(isValidParameters && isValidEmail){
                UserService.signUp(login, password,email, localeStr,userEnum, BALANCE_DEFAULT, DISCOUNT_DEFAULT);
            }

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }

    }



}
