package audiotrack.controller;

import audiotrack.controller.command.ActionCommand;
import audiotrack.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<Commands,ActionCommand> commands=new HashMap<Commands, ActionCommand>();

    public CommandProvider() {
        final Commands signUp = Commands.SIGN_UP;
        final Commands changeLocal= Commands.CHANGE_LOCAL;
        final Commands signIn= Commands.SIGN_IN;
        final Commands afterAuthentication=Commands.AFTER_AUTHENTICATION;
        final Commands showAllUsers=Commands.SHOW_USERS;
        final Commands afterRegistration=Commands.AFTER_REGISTRATION;
        final Commands addTrack=Commands.ADD_TRACK;
        final Commands beforeAddTrack=Commands.BEFORE_ADD_TRACK;
        final Commands logOut= Commands.LOG_OUT;
        final Commands showTracks=Commands.SHOW_TRACKS;
        final Commands deleteTrack=Commands.DELETE_TRACK;
        final Commands updateTrack=Commands.UPDATE_TRACK;
        final Commands addAlbum=Commands.ADD_ALBUM;
        final Commands addPlaylist=Commands.ADD_PLAYLIST;
        final Commands beforeUpdatingTrack=Commands.BEFORE_UPDATE_TRACK;
        final Commands addComment= Commands.ADD_COMMENT;
        final Commands addOrder= Commands.MAKE_ORDER;
        final  Commands updateUserDiscount= Commands.UPDATE_USER_DISCOUNT;
        final  Commands beforeUpdateUserDiscount=Commands.BEFORE_UPDATE_USER_DISCOUNT;
        final  Commands beforeAddComment=Commands.BEFORE_ADD_COMMENT;
        final  Commands showClientTracks=Commands.SHOW_CLIENT_TRACKS;
        final Commands showAllComments=Commands.SHOW_COMMENTS_TRACK;

        commands.put(signUp,new SignUpCommand());
        commands.put(changeLocal,new ChangeLocaleCommand());
        commands.put(signIn, new SignInCommand());
        commands.put(afterAuthentication, new AfterAuthenticationCommand());
        commands.put(showAllUsers, new ShowAllUsersCommand());
        commands.put(afterRegistration, new AfterRegistrationCommand());
        commands.put(addTrack, new AddTrackCommand());
        commands.put(beforeAddTrack, new BeforeAddTrackCommand());
        commands.put(logOut, new LogOutCommand());
        commands.put(showTracks, new ShowAllTracksCommand());
        commands.put(deleteTrack, new DeleteTrackCommand());
        commands.put(updateTrack, new UpdateTrackCommand());
        commands.put(addAlbum, new AddAlbumCommand());
        commands.put(addPlaylist, new AddPlaylistCommand());
        commands.put(beforeUpdatingTrack, new BeforeUpdateTrackCommand());
        commands.put(addComment, new AddCommentCommand());
        commands.put(addOrder, new AddOrderCommand());
        commands.put(updateUserDiscount, new UpdateUserDiscountCommand());
        commands.put(beforeUpdateUserDiscount, new BeforeUpdateUserDiscountCommand());
        commands.put(beforeAddComment, new BeforeAddCommentCommand());
        commands.put(showClientTracks, new ShowClientTracksCommand());
        commands.put(showAllComments, new ShowCommentsTrackCommand());



    }

    public ActionCommand takeCommand(String command){
        final String uppercaseCommand = command.toUpperCase();
        Commands commandName= Commands.valueOf(uppercaseCommand);
        return  commands.get(commandName);
    }


}
