package audiotrack.controller.command.impl;

import audiotrack.bean.Track;
import audiotrack.bean.User;
import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.OrderService;
import audiotrack.service.TrackService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddOrderCommand implements ActionCommand {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static Logger logger = Logger.getLogger(AddOrderCommand.class);
    public static final String COMMAND="audiotrack?command=show_tracks";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {
        String trackIdStr = req.getParameter(ParameterEnum.TRACK_ID.toString());
        int trackId=Integer.parseInt(trackIdStr);
        HttpSession session = req.getSession();
        String login=(String) session.getAttribute(ParameterEnum.LOGIN.toString());
        addOrder(req, trackId, login);
        ForwardRedirectHelper.provideRedirect(resp,COMMAND);

    }




    private void addOrder(HttpServletRequest req, int trackId, String login) {

        try {
            User user = getUser(trackId, login);
            double balance=user.getBalance();

            BigDecimal bigDecimal = new BigDecimal(balance);
            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

            HttpSession session = req.getSession();
            session.setAttribute(ParameterEnum.BALANCE.toString(), bigDecimal);

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }

    private User getUser(int trackId, String login) throws ServiceException {
        User user;
        UserServiceImpl userService = serviceFactory.getUserService();
        user=userService.getUserByLogin(login);
        TrackService trackService=serviceFactory.getTrackService();
        Track track;
        track= trackService.getTrackById(trackId);

        boolean isValidOrder = checkOrder(user, track);
        if(isValidOrder){
            OrderService orderService=serviceFactory.getOrderService();
            orderService.addOrder(user,track);
        }
        return user;
    }


    private boolean checkOrder(User user, Track track) {

        double trackPrice = getTrackPrice(user, track);
        Double userBalance=user.getBalance();
        double balanceAfterOrder = userBalance - trackPrice ;
        boolean isValidOrder=false;
        if(userBalance!=0 && balanceAfterOrder >=0){
            user.setBalance(balanceAfterOrder);
            isValidOrder=true;
        }
        return isValidOrder;
    }

    private double getTrackPrice(User user, Track track) {
        int userDiscount=user.getDiscount();
        double trackPrice=track.getPrice();
        double cashback;
        if(userDiscount!=0){
            cashback=(trackPrice*userDiscount)/100;
            trackPrice=trackPrice-cashback;
        }
        return trackPrice;
    }


}
