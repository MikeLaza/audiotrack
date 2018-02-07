package audiotrack.controller.command.impl.util;


public class AddUpdateTrackHelper {

    public static boolean checkParameters(String albumIdStr, String nameTrack, String durationStr, String priceStr)  {
        boolean isValid=true;
        boolean notNulls = ParameterValidator.isNotNulls(albumIdStr, nameTrack, durationStr, priceStr);
        if(!notNulls ){
            isValid=false;
        }else{
            boolean notEmptyParameters = ParameterValidator.isNotEmptyParameters(albumIdStr, nameTrack, durationStr, priceStr);
            if (!notEmptyParameters){
                isValid=false;
            }else {
                boolean validIntParameter = ParameterValidator.isValidIntParameter(albumIdStr,durationStr);
                boolean validDoubleParameter = ParameterValidator.isvValidDoubleParameter(priceStr);
                if (!validIntParameter || !validDoubleParameter){
                    isValid=false;
                }
            }

        }
        return isValid;

    }
}
