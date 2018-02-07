package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.CookieHelper;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {
        Cookie loginCookie = CookieHelper.getCookie(req, ParameterEnum.LOGIN.toString());
        Cookie commandCookie = CookieHelper.getCookie(req, ParameterEnum.COOKIE_COMMAND.toString());
        if(loginCookie!=null && commandCookie!=null){
            closeCookie(req,resp, loginCookie);
            closeCookie(req,resp, commandCookie);
        }
        String path = PageEnum.WELCOME_PAGE.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }

    private void closeCookie(HttpServletRequest req, HttpServletResponse resp, Cookie cookie) {
        int maxAge = 0;
        cookie.setMaxAge(maxAge);
        cookie.setValue(null);
        resp.addCookie(cookie);
        req.getSession().invalidate();
    }
}
