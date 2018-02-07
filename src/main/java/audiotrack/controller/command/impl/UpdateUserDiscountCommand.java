package audiotrack.controller.command.impl;

import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.controller.command.impl.util.ParameterValidator;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserDiscountCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(UpdateUserDiscountCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    public static final String COMMAND="audiotrack?command=show_users";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {

        String userIdStr=req.getParameter(ParameterEnum.USER_ID.toString());
        int userId=Integer.parseInt(userIdStr);
        String discountStr = req.getParameter(ParameterEnum.DISCOUNT.toString());
        boolean isValidDiscount=checkParameter(discountStr);
        if(isValidDiscount){
            Integer discount=Integer.parseInt(discountStr);

            UserServiceImpl userService = serviceFactory.getUserService();
            updateUserDiscount(req, userId, discount, userService);

            ForwardRedirectHelper.provideForward(req,resp,COMMAND);
        }else {
            ForwardRedirectHelper.provideForward(req,resp,COMMAND);
        }



    }

    private void updateUserDiscount(HttpServletRequest req, int userId, Integer discount, UserServiceImpl userService) {
        try {
            boolean isUpdated=userService.updateUser(userId,discount);

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }



    private boolean checkParameter(String discountSrt){
        boolean isValid=true;
        boolean notNulls = ParameterValidator.isNotNulls(discountSrt);
        if(!notNulls ){
            isValid=false;
        }else{
            boolean notEmptyParameters = ParameterValidator.isNotEmptyParameters(discountSrt);
            if (!notEmptyParameters){
                isValid=false;
            }else {
                boolean validIntParameter = ParameterValidator.isValidIntParameter(discountSrt);
                if (!validIntParameter){
                    isValid=false;
                }
            }

        }
        return isValid;
    }
}
