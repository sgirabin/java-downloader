# About Java Downloader

  Efficient Java Application to download large file.

  Technologies stack:
  * Spring 4
  * Castor
  * Xerces

  What make java downloader different:
  
  1. Use of Non-Blocking I/O from java.nio package
     
     Benefits Java NIO's non-blocking mode enables a thread to request reading data from a channel, and only get what is currently available, or nothing at all.
     When there is no data is currently available, rather remain blocked until data becomes available for reading, the thread can go on with something else.
    
    ~~~~ 
        @Override
        public void run() {
            try {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                logger.info("Downloaded size: " + fos.getChannel().size());
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
                Path path = Paths.get(downloadSite.getOutputFile());
                try {
                    Files.delete(path);
                } catch (IOException ex1) {
                    logger.error(ex.getMessage(), ex1);
                }
            }
        }
    ~~~~ 
  
  2. Support Concurrent download up to 5 sites (configurable)


# Run java-downloader

1. Download binary version from this URL
   https://github.com/sgirabin/java-downloader/blob/master/bin/download-app.zip

   Save it into your working directory.

2. Unzip download-appzip

3. Go to unzipped folder 

4. Edit download-app.sh
    ~~~~ 
    #!/bin/bash
    export JAVA_HOME=/usr/
    export PATH=$PATH:$JAVA_HOME/bin
    export CLASSPATH=$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/tools.jar

    BASEDIR=$(dirname "$0")
    $JAVA_HOME/bin/java -server -Xms2048m -Xmx2048m -XX:MaxMetaspaceSize=2048m -verbose:gc -XX:+HeapDumpOnOutOfMemoryError -cp "$BASEDIR/classes:$BASEDIR/lib/*:." sg.com.agoda.downloader.DownloadApp $* 
    ~~~~         
        
  Change JAVA_HOME with you Java installation path.

5. Save download-app.sh after modification

6. Run download-app.sh
    ~~~~
    ./download-app.sh
    ~~~~         

# Set Download Application

1. Go to "download-app" installation directory

2. Open "settings.xml"
    ~~~~  
    <?xml version="1.0" encoding="UTF-8"?>
    <download-setting>
        <download-job>2</download-job>
        <download-list>
            <protocol>http</protocol>
            <hostname>search.maven.org</hostname>
            <port>80</port>
            <targetFile>remotecontent?filepath=org/mockito/mockito-core/2.0.71-beta/mockito-core-2.0.71-beta.jar</targetFile>
            <outputFile>mockito-core-2.0.71-beta.jar</outputFile>
            <needAuthentication>false</needAuthentication>
        </download-list>
        <download-list>
            <protocol>http</protocol>
            <hostname>search.maven.org</hostname>
            <port>80</port>
            <targetFile>remotecontent?filepath=org/mockito/mockito-all/2.0.2-beta/mockito-all-2.0.2-beta.jar</targetFile>
            <outputFile>mockito-all-2.0.2-beta.jar</outputFile>
            <needAuthentication>false</needAuthentication>
        </download-list>
    </download-setting>
    ~~~~  

    Description of "settings.xml": 
    
    | FIELD         | DESCRIPTION                   | REQUIRED  |
    | ------------- |-----------------------------| :---------:|
    | download-job  | Number of download job       | Yes       | 
    | protocol      | http, https, ftp, sftp (required)| Yes | 
    | hostname|  server name (required)| Yes | 
    | port|  server port (required)| Yes | 
    | targetFile|  file to be downloaded (include path) (required)| Yes | 
    | outputFile|  where to save downloaded file (included path) (required)| Yes | 
    | needAuthentication |  true/false. True if need to authenticate to the server (optional)| No | 
    | username|  username to authenticate (optional)| No | 
    | password|  password to authenticate (optional) | No | 

3. Save "settings.xml"

