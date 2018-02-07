package audiotrack.controller.command.impl;

import audiotrack.controller.PageEnum;
import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.util.ForwardRedirectHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeforeAddTrackCommand implements ActionCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String path = PageEnum.BEFORE_ADD_TRACK.toString();
        ForwardRedirectHelper.provideForward(req,resp,path);
    }
}
