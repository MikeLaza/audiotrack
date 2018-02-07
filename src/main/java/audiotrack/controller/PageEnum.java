package audiotrack.controller;

public enum PageEnum {

    UNIVERSAL_PAGE("WEB-INF/jsp/main/universal-page.jsp"),
    WELCOME_PAGE("WEB-INF/jsp/main/welcome-page.jsp"),
    SHOW_USERS("WEB-INF/jsp/showUsers.jsp"),
    BEFORE_ADD_TRACK("WEB-INF/jsp/add-track.jsp"),
    SHOW_TRACKS("WEB-INF/jsp/showTracks.jsp"),
    BEFORE_UPDATE_TRACK("WEB-INF/jsp/update-track.jsp"),
    BEFORE_UPDATE_USER_DISCOUNT("WEB-INF/jsp/update-user-discount.jsp"),
    BEFORE_ADD_COMMENT("WEB-INF/jsp/add-comment.jsp"),
    SHOW_CLIENT_TRACKS("WEB-INF/jsp/showClientTracks.jsp"),
    SHOW_COMMENTS_TRACK("WEB-INF/jsp/showCommentsTrack.jsp");


    private String path;

    PageEnum(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
