/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import sg.com.agoda.downloader.model.DownloadSite;

/**
 *
 * @author isakrabin
 */
public class DownloadControllerTest {

    private DownloadController downloadController;

    @Mock
    private ThreadPoolTaskExecutor mockThreadPoolTaskExecutor;

    public DownloadControllerTest() {
        MockitoAnnotations.initMocks(this);
        downloadController = new DownloadController(5);
        downloadController.setDownloadJobExecutor(mockThreadPoolTaskExecutor);
    }

    @Test
    public void shouldInitializeDownloadController() {
        Assert.assertEquals(0, downloadController.getDownloadList().size());
        Assert.assertEquals(5, downloadController.getMaxDownload());
    }

    @Test
    public void shouldAddToDownloadListWhenTotalDownloadLessThanMaxDownload() {
        int currentSize = downloadController.getDownloadList().size();
        Assert.assertEquals(0, currentSize);

        DownloadSite mockDownloadConfig = Mockito.mock(DownloadSite.class);
        downloadController.addDownloadList(mockDownloadConfig);

        Assert.assertEquals(currentSize + 1, downloadController.getDownloadList().size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenMaxDownloadReach() {
        DownloadSite mockDownloadConfig = Mockito.mock(DownloadSite.class);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.addDownloadList(mockDownloadConfig);
    }

    @Test(expected = Exception.class)
    public void shouldNotStartDownloadWhenDownloadListIsEmpty() throws Exception {
        downloadController.start();
    }

    @Test
    public void shouldStartDownloadWhenSizeGreaterThanZero() throws Exception {
        DownloadSite mockDownloadConfig = Mockito.mock(DownloadSite.class);
        downloadController.addDownloadList(mockDownloadConfig);
        downloadController.start();
    }

    @Test
    public void shouldStopDownloadWhenHasActiveDownload() throws InterruptedException {
        downloadController.stop();
    }

    @Test
    public void shouldReturnTrueWhenThereIsActiveDownload() {
        Mockito.when(mockThreadPoolTaskExecutor.getActiveCount()).thenReturn(1);
        Assert.assertTrue(downloadController.hasActiveDownload());
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoActiveDownload() {
        Mockito.when(mockThreadPoolTaskExecutor.getActiveCount()).thenReturn(0);
        Assert.assertFalse(downloadController.hasActiveDownload());
    }
}
