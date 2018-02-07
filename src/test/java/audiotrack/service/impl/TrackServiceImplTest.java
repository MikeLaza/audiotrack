package audiotrack.service.impl;

import audiotrack.service.exception.ServiceException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class TrackServiceImplTest {
    private TrackServiceImpl trackService= TrackServiceImpl.getInstance();

    @DataProvider
    public static Object[][] providerAddTrack() {
        return new Object[][] {
                { 11, "mikelaza", 5,0,false},
                { 11, "mikelaza", 0,5,false},
                { 11, null, 0,5,false},
                { 11, "mikelaza", 4,3.4,true},

        };
    }

    @Test
    @UseDataProvider("providerAddTrack")
    public void shouldReturnFalseWhenInsertWrongParameterWhileAdding(int  albumId,String nameTrack,int  duration,double price, boolean expected) throws ServiceException {
        boolean actual=trackService.addTrack(albumId,nameTrack,duration,price);
        Assert.assertEquals(expected, actual);
    }

}
