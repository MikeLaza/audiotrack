package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.AddUpdateTrackHelper;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.TrackService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTrackCommand implements ActionCommand {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    public static final String COMMAND= "audiotrack?command=show_tracks";
    private static Logger logger = Logger.getLogger(AddTrackCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {

        String albumIdStr=req.getParameter(ParameterEnum.ALBUM_ID.toString());
        String nameTrackStr=req.getParameter(ParameterEnum.NAME_TRACK.toString());
        String durationStr=req.getParameter(ParameterEnum.DURATION.toString());
        String priceStr=req.getParameter(ParameterEnum.PRICE.toString());

        try {

            boolean isAdded=handleAdding(albumIdStr, nameTrackStr, durationStr, priceStr);
            if(!isAdded){
                prepareAttributes(req, albumIdStr, nameTrackStr, durationStr, priceStr);
                ForwardRedirectHelper.provideForward(req,resp, PageEnum.BEFORE_ADD_TRACK.toString());
            }else {
                ForwardRedirectHelper.provideRedirect(resp,COMMAND);
            }
        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }

    }

    private void prepareAttributes(HttpServletRequest req, String albumIdStr, String nameTrackStr, String durationStr, String priceStr) {
        req.setAttribute(ParameterEnum.ALBUM_ID.toString(),albumIdStr);
        req.setAttribute(ParameterEnum.NAME_TRACK.toString(),nameTrackStr);
        req.setAttribute(ParameterEnum.ALBUM_ID.toString(),albumIdStr);
        req.setAttribute(ParameterEnum.DURATION.toString(),durationStr);
        req.setAttribute(ParameterEnum.PRICE.toString(),priceStr);
    }

    private boolean handleAdding( String albumIdStr, String nameTrackStr, String durationStr, String priceStr) throws ServiceException {
        boolean isValidParameters= AddUpdateTrackHelper.checkParameters(albumIdStr, nameTrackStr, durationStr, priceStr);
        boolean isAdded=false;
        if(isValidParameters){

            int albumId=Integer.parseInt(albumIdStr);
            int duration=Integer.parseInt(durationStr);
            double price=Double.parseDouble(priceStr);

            TrackService trackService=serviceFactory.getTrackService();
            isAdded = trackService.addTrack(albumId, nameTrackStr, duration, price);

        }
        return isAdded;
    }


}
