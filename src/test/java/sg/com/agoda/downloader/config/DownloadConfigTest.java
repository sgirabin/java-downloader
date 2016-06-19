/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.config;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author isakrabin
 */
public class DownloadConfigTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidProtocol() {
        DownloadConfig downloadConfig = new DownloadConfig("ssh", "localhost", 22, "test.in", "test.out");
    }

    @Test
    public void shouldAllowHttpProtocol() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "test.out");
    }

    @Test
    public void shouldAllowHttpsProtocol() {
        DownloadConfig downloadConfig = new DownloadConfig("https", "localhost", 443, "test.in", "test.out");
    }

    @Test
    public void shouldAllowFtpProtocol() {
        DownloadConfig downloadConfig = new DownloadConfig("ftp", "localhost", 21, "test.in", "test.out");
    }

    @Test
    public void shouldAllowSFtpProtocol() {
        DownloadConfig downloadConfig = new DownloadConfig("sftp", "localhost", 22, "test.in", "test.out");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenHostnameIsNull() {
        DownloadConfig downloadConfig = new DownloadConfig("http", null , 80, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenEmptyHostname() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "", 80, "test.in","test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidHostname() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "   ", 80, "test.in", "test.out");
    }
    
    @Test
    public void shouldAllowValidHostname() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "test.out");
    }    

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenNullPort() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", null, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidPort() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", -1, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPortExceedMaximumPortNumber() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 65536, "test.in", "test.out");
    }

    @Test
    public void shouldAllowValidPort() {
        DownloadConfig downloadConfig1 = new DownloadConfig("sftp", "localhost", 0, "test.in", "test.out");
        DownloadConfig downloadConfig2 = new DownloadConfig("sftp", "localhost", 22, "test.in", "test.out");
        DownloadConfig downloadConfig3 = new DownloadConfig("sftp", "localhost", 65535, "test.in", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTargetFileIsNull() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, null, "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenEmptyTargetFile() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "", "test.out");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidTargetFile() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "    ", "test.out");
    }
    
    @Test
    public void shouldAllowValidTargetFile() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "test.out");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenOutputFileIsNull() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenEmptyOutputFile() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionGivenInvalidOutputFile() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "   ");
    }
    
    @Test
    public void shouldAllowValidOutputFile() {
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "test.out");
    }    

    @Test
    public void shouldReturnDownloadUrl() {
        String expected = "http://localhost:80/test.in";
        DownloadConfig downloadConfig = new DownloadConfig("http", "localhost", 80, "test.in", "test.out");

        Assert.assertEquals(expected, downloadConfig.getDownloadUrl());
    }

}
