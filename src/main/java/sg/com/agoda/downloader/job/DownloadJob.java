/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.job;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.log4j.Logger;
import sg.com.agoda.downloader.config.DownloadConfig;

/**
 *
 * @author isakrabin
 */
public class DownloadJob implements Runnable {
    
    final static Logger logger = Logger.getLogger(DownloadJob.class);
    
    private final DownloadConfig downloadConfig;
    
    private final URL url;
    private final ReadableByteChannel rbc;
    private final FileOutputStream fos;
    
    public DownloadJob(DownloadConfig downloadConfig) throws MalformedURLException, IOException {
        this.downloadConfig = downloadConfig;
        this.url = new URL(downloadConfig.getDownloadUrl());
        this.rbc = Channels.newChannel(url.openStream());
        this.fos = new FileOutputStream(downloadConfig.getOutputFile());
    }
    
    public DownloadConfig getDownloadConfig() {
        return downloadConfig;
    }
    
    @Override
    public void run() {
        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            logger.info("Downloaded size: " + fos.getChannel().size());
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            Path path = Paths.get(downloadConfig.getOutputFile());
            try {
                Files.delete(path);
            } catch (IOException ex1) {
                logger.error(ex.getMessage(), ex1);
            }
        }
    }
    
}
