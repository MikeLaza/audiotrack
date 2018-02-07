package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeforeUpdateUserDiscountCommand implements ActionCommand {


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)   {
        String id=req.getParameter(ParameterEnum.USER_ID.toString());
        String discount=req.getParameter(ParameterEnum.DISCOUNT.toString());

        req.setAttribute(ParameterEnum.DISCOUNT.toString(),discount);
        req.setAttribute(ParameterEnum.USER_ID.toString(), id);

        String path = PageEnum.BEFORE_UPDATE_USER_DISCOUNT.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }
}
