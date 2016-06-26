/*
 * Copyright @2016 by Isak Rabin
 */
package sg.com.agoda.downloader.model;

import sg.com.agoda.downloader.common.StringUtil;
import sg.com.agoda.downloader.constant.SupportedProtocol;

/**
 *
 * @author isakrabin
 */
public class DownloadSite {

    private static final int MAX_PORT_NUMBER = 65535;

    private String protocol;
    private String hostname;
    private Integer port;
    private String targetFile;
    private String outputFile;
    private String username;
    private String password;
    private boolean needAuthentication;

    public DownloadSite() {
    }

    public DownloadSite(String protocol, String hostname, Integer port, String targetFile, String outputFile) {
        if (isValidProtocol(protocol)) {
            this.protocol = protocol;
        }

        if (isValidPort(port)) {
            this.port = port;
        }

        if (isValidHostname(hostname)) {
            this.hostname = hostname;
        }

        if (isValidTargetFile(targetFile)) {
            this.targetFile = targetFile;
        }

        if (isValidOutputFile(outputFile)) {
            this.outputFile = outputFile;
        }
        
        this.needAuthentication = false;
    }

    public DownloadSite(String protocol, String hostname, Integer port, String username, String password, String targetFile, String outputFile) {
        this(protocol, hostname, port, targetFile, outputFile);
        this.username = username;
        this.password = password;
        this.needAuthentication = true;
    }

    private boolean isValidProtocol(String protocol) {
        if (!(SupportedProtocol.HTTP.name().equalsIgnoreCase(protocol)
                || SupportedProtocol.HTTPS.name().equalsIgnoreCase(protocol)
                || SupportedProtocol.FTP.name().equalsIgnoreCase(protocol)
                || SupportedProtocol.SFTP.name().equalsIgnoreCase(protocol))) {
            throw new IllegalArgumentException(String.format(
                    "Protocol: %s is not allowed. %n"
                    + "Allowed protocols are: http, https, ftp, and sftp.",
                    protocol
            ));
        }

        return true;
    }

    private boolean isValidPort(Integer port) {
        if (port == null || port.compareTo(0) < 0 || port.compareTo(MAX_PORT_NUMBER) > 0) {
            throw new IllegalArgumentException(String.format(
                    "Port: %d is not allowed. %n"
                    + "Allowed port are between 0-65535.",
                    port
            ));
        }
        return true;
    }

    private boolean isValidHostname(String hostname) {
        if (StringUtil.isEmptyString(hostname)) {
            throw new IllegalArgumentException("Hostname cannot be empty.");
        }
        return true;
    }

    private boolean isValidTargetFile(String targetFile) {
        if (StringUtil.isEmptyString(targetFile)) {
            throw new IllegalArgumentException("Target File cannot be empty.");
        }
        return true;
    }

    private boolean isValidOutputFile(String outputFile) {
        if (StringUtil.isEmptyString(outputFile)) {
            throw new IllegalArgumentException("Output File cannot be empty.");
        }
        return true;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNeedAuthentication() {
        return needAuthentication;
    }

    public void setNeedAuthentication(boolean needAuthentication) {
        this.needAuthentication = needAuthentication;
    }

    public String getDownloadUrl() {
        StringBuilder sb = new StringBuilder();

        sb.append(protocol);
        sb.append("://");
        sb.append(hostname);
        sb.append(":");
        sb.append(port);
        sb.append("/");
        sb.append(targetFile);

        return sb.toString();
    }

}
