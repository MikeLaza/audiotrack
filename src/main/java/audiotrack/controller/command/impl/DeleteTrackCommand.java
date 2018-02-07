package audiotrack.controller.command.impl;

import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ErrorLogger;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.service.exception.ServiceException;
import audiotrack.service.factory.ServiceFactory;
import audiotrack.service.impl.TrackServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTrackCommand implements ActionCommand {
    public static final String COMMAND="audiotrack?command=show_tracks";
    private static Logger logger = Logger.getLogger(DeleteTrackCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {
        String idStr=req.getParameter(ParameterEnum.TRACK_ID.toString());
        Integer id=Integer.parseInt(idStr);
        deleteTrack(req, id);
        ForwardRedirectHelper.provideRedirect(resp,COMMAND);
    }

    private void deleteTrack(HttpServletRequest req, Integer id) {
        TrackServiceImpl trackService= serviceFactory.getTrackService();
        try {
            trackService.deleteTrack(id);
        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }
}
