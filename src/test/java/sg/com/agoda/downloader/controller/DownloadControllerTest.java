/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sg.com.agoda.downloader.config.DownloadConfig;

/**
 *
 * @author isakrabin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-context-test.xml")
public class DownloadControllerTest {

    @Autowired
    private DownloadController downloadController;

    @Test
    public void shouldInitializeDownloadController() {
        Assert.assertNotNull(downloadController);
        Assert.assertEquals(5, downloadController.getMaxDownload());
        Assert.assertNotNull(downloadController.getDownloadJobExecutor());
    }

    @Test
    public void shouldAddToDownloadListWhenTotalDownloadLessThanMaxDownload() {
        Assert.assertEquals(0, downloadController.getDownloadList().size());

        DownloadConfig mockDownloadConfig = Mockito.mock(DownloadConfig.class);
        downloadController.addDownloadList(mockDownloadConfig);

        Assert.assertEquals(1, downloadController.getDownloadList().size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenMaxDownloadReach() {
        DownloadConfig mockDownloadConfig = Mockito.mock(DownloadConfig.class);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
    }

    @Test
    public void shouldStartDownloadWhenSizeGreaterThanZero() {

    }

    @Test
    public void shouldStopDownload() {

    }

    @Test
    public void shouldReturnTrueWhenThereIsActiveDownload() {

    }

    @Test
    public void shouldReturnFalseWhenThereIsNoActiveDownload() {

    }
}
