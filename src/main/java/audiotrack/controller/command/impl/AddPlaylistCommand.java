package audiotrack.controller.command.impl;

import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.PlaylistService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPlaylistCommand implements ActionCommand {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static Logger logger = Logger.getLogger(AddPlaylistCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String namePlaylist=req.getParameter(ParameterEnum.NAME_PLAYLIST.toString());
        PlaylistService  playlistService=serviceFactory.getPlaylistService();
        try {
            playlistService.addPlaylist(namePlaylist);
        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }


    }
}
