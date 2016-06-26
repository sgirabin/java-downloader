/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.config;

import sg.com.agoda.downloader.model.DownloadSite;
import java.util.List;

/**
 *
 * @author isakrabin
 */
public class DownloadSetting {

    private int downloadJob;
    private String outputFolder;
    private List<DownloadSite> downloadList;

    public int getDownloadJob() {
        return downloadJob;
    }

    public void setDownloadJob(int downloadJob) {
        this.downloadJob = downloadJob;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }
    
    public List<DownloadSite> getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(List<DownloadSite> downloadList) {
        this.downloadList = downloadList;
    }

}
