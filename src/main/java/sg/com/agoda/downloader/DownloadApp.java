/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.com.agoda.downloader.config.DownloadConfig;
import sg.com.agoda.downloader.controller.DownloadController;

/**
 *
 * @author isakrabin
 */
public class DownloadApp {

    final static Logger logger = Logger.getLogger(DownloadApp.class);
 
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        
        DownloadController downloadController = context.getBean(DownloadController.class);
        
        DownloadConfig downloadConfig = new DownloadConfig("http", "buildlogs.centos.org", 80, "rolling/7/isos/x86_64/CentOS-7-x86_64-DVD.iso", "CentOS-7-x86_64-DVD.iso");
        downloadController.addDownloadList(downloadConfig);
        downloadController.start();
        for (;;) {
            boolean hasActiveSession = downloadController.hasActiveDownload();
            if (!hasActiveSession) {
                downloadController.stop();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                logger.warn(ex.getMessage(), ex);
            }
        }

    }

}
