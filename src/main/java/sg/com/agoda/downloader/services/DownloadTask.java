/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.services;

/**
 *
 * @author isakrabin
 */
public class DownloadTask implements Runnable{
    
    private String targetUrl;
    
    public DownloadTask(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
