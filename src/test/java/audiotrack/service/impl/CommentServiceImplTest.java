package audiotrack.service.impl;

import audiotrack.service.exception.ServiceException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CommentServiceImplTest {
    private final CommentServiceImpl commentService= CommentServiceImpl.getInstance();


    @DataProvider
    public static Object[][] dataProviderAddComment() {
        return new Object[][] {
                { 1, 6,null,false},
                { 1, 6,"",false},
                { 3, 6,"makdddddd",true},

        };
    }
    @Test
    @UseDataProvider("dataProviderAddComment")
    public void shouldReturnFalseWhenInsertWrongParameterWhileAddingComment(int userId, int trackId, String text, boolean expected) throws ServiceException {
        boolean actual=commentService.addComment(userId,trackId,text);
         Assert.assertEquals(expected, actual);

    }


}
