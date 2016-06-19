/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.common;

/**
 *
 * @author isakrabin
 */
public class StringUtil {

    public static boolean isEmptyString(String str) {
        return (str == null || str.isEmpty() || str.trim().isEmpty());
    }

}
