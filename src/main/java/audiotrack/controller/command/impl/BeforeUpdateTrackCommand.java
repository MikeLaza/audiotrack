package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.controller.command.impl.util.TrackHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BeforeUpdateTrackCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String albumId=req.getParameter(ParameterEnum.ALBUM_ID.toString());
        String nameTrack=req.getParameter(ParameterEnum.NAME_TRACK.toString());
        String duration=req.getParameter(ParameterEnum.DURATION.toString());
        String price=req.getParameter(ParameterEnum.PRICE.toString());
        String trackId=req.getParameter(ParameterEnum.TRACK_ID.toString());
        String nameAlbum=req.getParameter(ParameterEnum.NAME_ALBUM.toString());

        prepareAttributes(req, albumId, nameTrack, duration, price, trackId, nameAlbum);
        String path = PageEnum.BEFORE_UPDATE_TRACK.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }

    private void prepareAttributes(HttpServletRequest req, String albumId, String nameTrack, String duration, String price, String trackId, String nameAlbum) {
        req.setAttribute(ParameterEnum.TRACK_ID_FOR_REQUEST.toString(), trackId);
        req.setAttribute(ParameterEnum.ALBUM_ID.toString(), albumId);
        req.setAttribute(ParameterEnum.NAME_TRACK.toString(), nameTrack);
        req.setAttribute(ParameterEnum.DURATION.toString(), duration);
        req.setAttribute(ParameterEnum.PRICE.toString(), price);
        req.setAttribute(ParameterEnum.NAME_ALBUM.toString(), nameAlbum);
    }
}
