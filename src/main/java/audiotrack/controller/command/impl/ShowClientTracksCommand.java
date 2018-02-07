package audiotrack.controller.command.impl;

import audiotrack.bean.Track;
import audiotrack.controller.PageEnum;
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
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowClientTracksCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(ShowClientTracksCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final int RECORDS_PER_PAGE = 6;
    private static final int FIRST = 1;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession();
        String login=(String) session.getAttribute(ParameterEnum.LOGIN.toString());
        prepareUsersTrackList(req, login);
        String path = PageEnum.SHOW_CLIENT_TRACKS.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }

    private void prepareUsersTrackList(HttpServletRequest req, String login) {
        try {

            int page = FIRST;
            String pageStr = req.getParameter(ParameterEnum.PAGE.toString());
            if(pageStr != null){
                page = Integer.parseInt(pageStr);
            }
            handleClientPaging(req, login, page);

        } catch (ServiceException e) {
            ErrorLogger.logError(logger,req,e);
        }
    }

    private void handleClientPaging(HttpServletRequest req, String login, int page) throws ServiceException {
        TrackServiceImpl trackService = serviceFactory.getTrackService();
        int startIndex = (page - 1) * RECORDS_PER_PAGE;

        List<Track> trackList = trackService.getClientTracksByLogin(login,startIndex,RECORDS_PER_PAGE);
        int countRecords=trackService.getCountUserTracks(login);
        int countPages = (int) Math.ceil(countRecords * 1.0 / RECORDS_PER_PAGE);

        req.setAttribute(ParameterEnum.TRACK_LIST.toString(), trackList);
        req.setAttribute(ParameterEnum.COUNT_PAGES.toString(),countPages);
        req.setAttribute(ParameterEnum.CORRENT_PAGE.toString(),page);
        String command=req.getParameter(ParameterEnum.COMMAND.toString());
        req.setAttribute(ParameterEnum.COMMAND.toString(),command);
    }
}
