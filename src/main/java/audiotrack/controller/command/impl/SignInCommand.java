package audiotrack.controller.command.impl;

import audiotrack.bean.User;
import audiotrack.bean.UserEnum;
import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.controller.command.impl.util.SignInUpHelper;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(SignInCommand.class);
    public static  String AFTER_AUTHENTICATION="audiotrack?command=after_authentication";

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)   {
        String login = request.getParameter(ParameterEnum.LOGIN.toString());
        String password = request.getParameter(ParameterEnum.PASSWORD.toString());

        try {
            UserServiceImpl userService = serviceFactory.getUserService();
            boolean isValidParameters= SignInUpHelper.checkParameters(login, password);

            if(isValidParameters){
                User user = userService.signIn(login, password);
                prepareSessionAttributes(request, response, user);
            }else {
                ForwardRedirectHelper.provideForward(request, response, PageEnum.WELCOME_PAGE.toString());
            }

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,request,e);
            ForwardRedirectHelper.provideForward(request, response, PageEnum.WELCOME_PAGE.toString());
        }
        ForwardRedirectHelper.provideRedirect(response,AFTER_AUTHENTICATION);

    }

    private void prepareSessionAttributes(HttpServletRequest request, HttpServletResponse response, User user) {

        String loginUser = user.getLoginUser();
        Cookie cookieLogin = new Cookie(ParameterEnum.LOGIN.toString(), loginUser);
        response.addCookie(cookieLogin);
        HttpSession session = request.getSession(true);
        String local=user.getLocale();
        session.setAttribute(ParameterEnum.USER_LOCAL.toString(),local);
        session.setAttribute(ParameterEnum.LOGIN.toString(), loginUser);
        UserEnum userEnum = user.getUserEnum();
        String role= userEnum.getValue();

        if (role.equals(ParameterEnum.ADMIN.toString())) {
            session.setAttribute(ParameterEnum.ROLE.toString(), ParameterEnum.ADMIN.toString());
        } else {
            session.setAttribute(ParameterEnum.ROLE.toString(), ParameterEnum.CLIENT.toString());
        }
        session.setAttribute(ParameterEnum.LOGIN.toString(), loginUser);
        session.setAttribute(ParameterEnum.PAGE.toString(), PageEnum.UNIVERSAL_PAGE);
        double balance=user.getBalance();
        session.setAttribute(ParameterEnum.BALANCE.toString(), balance);
    }



}
