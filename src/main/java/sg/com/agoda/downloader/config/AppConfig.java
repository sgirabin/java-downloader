/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author isakrabin
 */
@Component
public class AppConfig {

    @Value("${download.max.input}")
    private int maxDownload;

    @Value("${download.settings}")
    private String downloadSettings;

    public int getMaxDownload() {
        return maxDownload;
    }

    public void setMaxDownload(int maxDownload) {
        this.maxDownload = maxDownload;
    }

    public String getDownloadSettings() {
        return downloadSettings;
    }

    public void setDownloadSettings(String downloadSettings) {
        this.downloadSettings = downloadSettings;
    }

}
