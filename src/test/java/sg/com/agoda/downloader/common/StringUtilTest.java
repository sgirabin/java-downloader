/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.common;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author isakrabin
 */
public class StringUtilTest {

    @Test
    public void shouldReturnTrueGivenNullString() {
        boolean isEmptyString = StringUtil.isEmptyString(null);
        Assert.assertTrue(isEmptyString);
    }

    @Test
    public void shouldReturnTrueGivenEmptyString() {
        boolean isEmptyString = StringUtil.isEmptyString("");
        Assert.assertTrue(isEmptyString);
    }

    @Test
    public void shouldReturnTrueGivenStringWithEmptySpaces() {
        boolean isEmptyString = StringUtil.isEmptyString("   ");
        Assert.assertTrue(isEmptyString);
    }
    
    @Test
    public void shouldReturnFalseGivenNonEmptyString() {
        boolean isEmptryString = StringUtil.isEmptyString("   Test   ");
        Assert.assertFalse(isEmptryString);
    }

}
