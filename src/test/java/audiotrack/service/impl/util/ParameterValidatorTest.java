package audiotrack.service.impl.util;

import audiotrack.controller.command.impl.util.ParameterValidator;
import org.junit.Assert;
import org.junit.Test;

public class ParameterValidatorTest {


    @Test
    public void shouldThrowExceptionWhenInsertWrongMail()  {
        boolean actual = ParameterValidator.isValidEmail("mike.lazarenko.com");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowExceptionWhenInsertWrongLogin()  {

        boolean actual = ParameterValidator.isValidLogin("mike");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowExceptionWhenInsertWrongPassword()  {
        boolean actual = ParameterValidator.isValidPassword("12345678");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowExceptionWhenInsertNullsParameter()  {
        boolean actual = ParameterValidator.isNotNulls(null, "fs");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowExceptionWhenInserEmptyParameters()  {
        boolean actual = ParameterValidator.isNotEmptyParameters("", "dfsf");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowExceptionWhenInsertWrongIndParameter()  {
        boolean actual = ParameterValidator.isValidIntParameter("123g");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void shouldThrowExceptionWhenInsertWrongDoubleParameter()  {
        boolean actual = ParameterValidator.isvValidDoubleParameter("34ed");
        boolean expected=false;
        Assert.assertEquals(expected,actual);
    }
}
