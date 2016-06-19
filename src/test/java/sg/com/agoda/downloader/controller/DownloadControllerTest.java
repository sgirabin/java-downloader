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
import sg.com.agoda.downloader.config.DownloadConfig;

/**
 *
 * @author isakrabin
 */
public class DownloadControllerTest {

    @Mock
    private ThreadPoolTaskExecutor downloadJobExecutor;

    public DownloadControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSetMaxDownload() {
        DownloadController controller = new DownloadController(5);
        Assert.assertEquals(5, controller.getMaxDownload());
    }

    @Test
    public void shouldAddToDownloadListWhenTotalDownloadLessThanMaxDownload() {
        DownloadController controller = new DownloadController(5);

        Assert.assertEquals(0, controller.getDownloadList().size());

        DownloadConfig mockDownloadConfig = Mockito.mock(DownloadConfig.class);
        controller.addDownloadList(mockDownloadConfig);

        Assert.assertEquals(1, controller.getDownloadList().size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenMaxDownloadReach() {
        DownloadController controller = new DownloadController(5);

        DownloadConfig mockDownloadConfig = Mockito.mock(DownloadConfig.class);
        controller.addDownloadList(mockDownloadConfig);
        controller.addDownloadList(mockDownloadConfig);
        controller.addDownloadList(mockDownloadConfig);
        controller.addDownloadList(mockDownloadConfig);
        controller.addDownloadList(mockDownloadConfig);
        controller.addDownloadList(mockDownloadConfig);
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
