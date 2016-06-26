/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import sg.com.agoda.downloader.model.DownloadSite;
import sg.com.agoda.downloader.job.DownloadJob;

/**
 *
 * @author isakrabin
 */
@Controller
public class DownloadController {

    final static Logger logger = Logger.getLogger(DownloadController.class);

    @Autowired
    private ThreadPoolTaskExecutor downloadJobExecutor;

    private int maxDownload = 5;

    private final List<DownloadSite> downloadList = new ArrayList<>();

    public DownloadController(int maxDownload) {
        this.maxDownload = maxDownload;
    }

    public ThreadPoolTaskExecutor getDownloadJobExecutor() {
        return downloadJobExecutor;
    }

    public void setDownloadJobExecutor(ThreadPoolTaskExecutor downloadJobExecutor) {
        this.downloadJobExecutor = downloadJobExecutor;
    }
    
    public int getMaxDownload() {
        return maxDownload;
    }

    public void setMaxDownload(int maxDownload) {
        this.maxDownload = maxDownload;
    }

    public void addDownloadList(DownloadSite downloadConfig) {
        if (downloadList.size() < maxDownload) {
            this.downloadList.add(downloadConfig);
        } else {
            throw new IndexOutOfBoundsException(String.format("Maximum %d download has been reached.", maxDownload));
        }
    }

    public List<DownloadSite> getDownloadList() {
        return downloadList;
    }

    public void start() throws Exception {
        if (downloadList.isEmpty()) {
            throw new Exception("Download list is empty.");
        }
        
        downloadList.stream().forEach((config) -> {
            try {
                DownloadJob downloadJob = new DownloadJob(config);
                downloadJobExecutor.execute(downloadJob);
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        });
    }

    public void stop() {
        if (hasActiveDownload()) {
             downloadJobExecutor.shutdown();
        }
    }

    public boolean hasActiveDownload() {
        return (downloadJobExecutor.getActiveCount() > 0);
    }

}
