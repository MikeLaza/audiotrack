package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfterAuthenticationCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        final String path = PageEnum.UNIVERSAL_PAGE.toString();
        ForwardRedirectHelper.provideForward(request,response,path);
    }
}
