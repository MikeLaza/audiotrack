package audiotrack.controller.command.impl;

import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.AlbumService;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAlbumCommand implements ActionCommand {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static Logger logger = Logger.getLogger(AddAlbumCommand.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String singerIdStr=req.getParameter(ParameterEnum.SINGER_ID.toString());
        String nameAlbum=req.getParameter(ParameterEnum.NAME_ALBUM.toString());
        addAlbum(req, singerIdStr, nameAlbum);

    }

    private void addAlbum(HttpServletRequest req, String singerIdStr, String nameAlbum) {
        try {
            AlbumService albumService=serviceFactory.getAlbumService();
            albumService.addAlbum(singerIdStr,nameAlbum);
        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }
}
