/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader;

import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.oxm.Unmarshaller;
import sg.com.agoda.downloader.config.DownloadSetting;

/**
 *
 * @author isakrabin
 */
public class DownloadAppTest {

    @Test
    public void shouldLoadSetting() throws IOException {

        DownloadSetting setting = new DownloadSetting();

        Unmarshaller mockUnmarshaller = Mockito.mock(Unmarshaller.class);
        Mockito.when(mockUnmarshaller.unmarshal(Matchers.any(StreamSource.class))).thenReturn(setting);

        DownloadApp app = new DownloadApp();
        app.setUnmarshaller(mockUnmarshaller);
        
        DownloadSetting result = app.loadSettings("settings.xml");
        Assert.assertEquals(setting, result);
    }

}
