/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author isakrabin
 */

public class DownloadManager {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        
    }

}
