package audiotrack.controller.command.impl;

import audiotrack.bean.Track;
import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.AddUpdateTrackHelper;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.TrackService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.TrackServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTrackCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(UpdateTrackCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    public static final String COMMAND="audiotrack?command=show_tracks";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {

        String idStr=req.getParameter(ParameterEnum.TRACK_ID.toString());
        String albumIdStr=req.getParameter(ParameterEnum.ALBUM_ID.toString());
        String nameTrack=req.getParameter(ParameterEnum.NAME_TRACK.toString());
        String durationStr=req.getParameter(ParameterEnum.DURATION.toString());
        String priceStr=req.getParameter(ParameterEnum.PRICE.toString());
        String nameAlbum=req.getParameter(ParameterEnum.NAME_ALBUM.toString());

        try {
            boolean isUpdated=handleUpdate( idStr, albumIdStr, nameTrack, durationStr, priceStr, nameAlbum);
            if (!isUpdated){
                showBeforeUpdate(req, resp, idStr);
            }else {
                ForwardRedirectHelper.provideForward(req,resp,COMMAND);
            }

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }

    }


    private void showBeforeUpdate(HttpServletRequest req, HttpServletResponse resp, String idStr) throws ServiceException {
        int id =Integer.parseInt(idStr);
        TrackServiceImpl trackService = serviceFactory.getTrackService();
        Track track= trackService.getTrackById(id);

        Integer trackId = track.getId();
        req.setAttribute(ParameterEnum.TRACK_ID_FOR_REQUEST.toString(), trackId);
        int idAlbum = track.getIdAlbum();
        req.setAttribute(ParameterEnum.ALBUM_ID.toString(), idAlbum);
        String nameTrack = track.getNameTrack();
        req.setAttribute(ParameterEnum.NAME_TRACK.toString(), nameTrack);
        int duration = track.getDuration();
        req.setAttribute(ParameterEnum.DURATION.toString(), duration);
        double price = track.getPrice();
        req.setAttribute(ParameterEnum.PRICE.toString(), price);
        String nameAlbum = track.getNameAlbum();
        req.setAttribute(ParameterEnum.NAME_ALBUM.toString(), nameAlbum);

        String path = PageEnum.BEFORE_UPDATE_TRACK.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }

    private boolean handleUpdate( String idStr, String albumIdStr, String nameTrack, String durationStr, String priceStr, String nameAlbum) throws ServiceException {
        boolean isValidParameters=AddUpdateTrackHelper.checkParameters(nameAlbum, nameTrack, durationStr, priceStr);
        boolean isUpdated=false;
        if(isValidParameters){

            int albumId=Integer.parseInt(albumIdStr);
            int duration=Integer.parseInt(durationStr);
            double price=Double.parseDouble(priceStr);
            int id=Integer.parseInt(idStr);

            TrackService trackService=serviceFactory.getTrackService();
            isUpdated = trackService.updateTrack(id, albumId, nameTrack, duration, price, nameAlbum);

        }
        return isUpdated;
    }
}
