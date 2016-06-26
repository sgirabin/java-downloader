/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import sg.com.agoda.downloader.config.DownloadSetting;
import sg.com.agoda.downloader.config.AppConfig;
import sg.com.agoda.downloader.controller.DownloadController;

/**
 *
 * @author isakrabin
 */
public class DownloadApp {

    final static Logger logger = Logger.getLogger(DownloadApp.class);

    final static String APP_CONFIG = "settings.xml";

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void saveSettings(DownloadSetting appConfig) throws IOException {

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(APP_CONFIG);
            this.marshaller.marshal(appConfig, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public DownloadSetting loadSettings(String filename) throws FileNotFoundException, IOException {
        FileInputStream is = null;
        DownloadSetting setting = null;
        try {
            is = new FileInputStream(APP_CONFIG);
            setting = (DownloadSetting) this.unmarshaller.unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return setting;
    }

    public static void main(String[] args) throws IOException, Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        DownloadApp application = (DownloadApp) context.getBean(DownloadApp.class);

        AppConfig applicationConfig = (AppConfig) context.getBean(AppConfig.class);
        DownloadSetting downloadSetting = application.loadSettings(applicationConfig.getDownloadSettings());
        if (applicationConfig.getMaxDownload() < downloadSetting.getDownloadJob()) {
            throw new Exception(String.format("Maximum Download is %d %n. Please reduce number of downloads.", applicationConfig.getMaxDownload()));
        }
        logger.info(String.format("Found %d download job", downloadSetting.getDownloadJob()));

        DownloadController controller = context.getBean(DownloadController.class);
        controller.setMaxDownload(applicationConfig.getMaxDownload());
        downloadSetting.getDownloadList().stream().forEach((downloadSite) -> {
            logger.info(downloadSite.toString());
            controller.addDownloadList(downloadSite);
        });

        logger.info("Starting all download.");
        controller.start();
        
        for (;;) {
            boolean hasActiveSession = controller.hasActiveDownload();
            if (!hasActiveSession) {
                logger.info("All Download has finished.");
                controller.stop();
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
