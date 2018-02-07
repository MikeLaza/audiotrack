package audiotrack.controller.command.impl.util;

import javax.servlet.http.HttpServletRequest;

public class TrackHelper {

    String id;
    String albumId;
    String nameTrack;
    String duration;
    String price;

    public TrackHelper() {
    }

    public  void parseRequest(HttpServletRequest req){

        id=req.getParameter(ParameterEnum.TRACK_ID.toString());
        albumId =req.getParameter(ParameterEnum.ALBUM_ID.toString());

        nameTrack=req.getParameter(ParameterEnum.NAME_TRACK.toString());
        duration=req.getParameter(ParameterEnum.DURATION.toString());

        price=req.getParameter(ParameterEnum.PRICE.toString());
    }

    public String getId() {
        return id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getNameTrack() {
        return nameTrack;
    }

    public String getDuration() {
        return duration;
    }

    public String getPrice() {
        return price;
    }
}
