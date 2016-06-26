/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author isakrabin
 */
public class DownloadSiteTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidProtocol() {
        DownloadSite downloadConfig = new DownloadSite("ssh", "localhost", 22, "test.in", "test.out");
    }

    @Test
    public void shouldAllowHttpProtocol() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "test.out");
    }

    @Test
    public void shouldAllowHttpsProtocol() {
        DownloadSite downloadConfig = new DownloadSite("https", "localhost", 443, "test.in", "test.out");
    }

    @Test
    public void shouldAllowFtpProtocol() {
        DownloadSite downloadConfig = new DownloadSite("ftp", "localhost", 21, "test.in", "test.out");
    }

    @Test
    public void shouldAllowSFtpProtocol() {
        DownloadSite downloadConfig = new DownloadSite("sftp", "localhost", 22, "test.in", "test.out");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenHostnameIsNull() {
        DownloadSite downloadConfig = new DownloadSite("http", null , 80, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenEmptyHostname() {
        DownloadSite downloadConfig = new DownloadSite("http", "", 80, "test.in","test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidHostname() {
        DownloadSite downloadConfig = new DownloadSite("http", "   ", 80, "test.in", "test.out");
    }
    
    @Test
    public void shouldAllowValidHostname() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "test.out");
    }    

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenNullPort() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", null, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidPort() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", -1, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPortExceedMaximumPortNumber() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 65536, "test.in", "test.out");
    }

    @Test
    public void shouldAllowValidPort() {
        DownloadSite downloadConfig1 = new DownloadSite("sftp", "localhost", 0, "test.in", "test.out");
        DownloadSite downloadConfig2 = new DownloadSite("sftp", "localhost", 22, "test.in", "test.out");
        DownloadSite downloadConfig3 = new DownloadSite("sftp", "localhost", 65535, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTargetFileIsNull() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, null, "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenEmptyTargetFile() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidTargetFile() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "    ", "test.out");
    }
    
    @Test
    public void shouldAllowValidTargetFile() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "test.out");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenOutputFileIsNull() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenEmptyOutputFile() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidOutputFile() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "   ");
    }
    
    @Test
    public void shouldAllowValidOutputFile() {
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "test.out");
    }    

    @Test
    public void shouldReturnDownloadUrl() {
        String expected = "http://localhost:80/test.in";
        DownloadSite downloadConfig = new DownloadSite("http", "localhost", 80, "test.in", "test.out");

        Assert.assertEquals(expected, downloadConfig.getDownloadUrl());
    }

}
