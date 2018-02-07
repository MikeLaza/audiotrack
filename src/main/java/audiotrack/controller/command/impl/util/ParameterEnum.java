package audiotrack.controller.command.impl.util;

public enum ParameterEnum {
    LOGIN("login"),
    PASSWORD("password"),
    EMAIL("email"),
    IS_ADMIN("is_admin"),
    ROLE("role"),
    CLIENT("client"),
    PAGE("page"),
    USER_LOCAL("userLocal"),
    ADMIN("admin"),
    ALBUM_ID("album_id"),
    NAME_TRACK("name_track"),
    DURATION("duration"),
    PRICE("price"),
    NAME_PLAYLIST("name_playlist"),
    LOCAL("local"),
    WELCOME_LOCAL("welcomeLocal"),
    SINGER_ID("singer_id"),
    NAME_ALBUM("name_album"),
    TEXT_COMMENT("text"),
    TRACK_ID("track_id"),
    TRACK_ID_FOR_REQUEST("trackId"),
    USER_LOGIN("userLogin"),
    USER_ID("user_id"),
    COOKIE_COMMAND("cookieCommand"),
    TRACK_LIST("trackList"),
    USER_LIST("userList"),
    COMMENTS_LIST("commentsList"),
    DISCOUNT("discount"),
    ERROR_INFO("error_info"),
    LOCALE("locale"),
    COMMENT_ID("comment_id"),
    TRACK("track"),
    TEXT("text"),
    ORDER_ID("order_id"),
    PLAYLIST_ID("playlist_id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    BALANCE("balance"),
    COMMAND("command"),
    COUNT_PAGES("countPages"),
    CORRENT_PAGE("currentPage"),
    IMG("img");

    private String value;
    ParameterEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
