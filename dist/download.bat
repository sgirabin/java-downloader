
SET JAVA_HOME=/usr/
SET PATH=%PATH%:%JAVA_HOME%/bin
SET CLASSPATH=%JAVA_HOME%/jre/lib/ext:%JAVA_HOME%/lib/tools.jar


SET BASEDIR=%~dp0
%JAVA_HOME%/bin/java -server -Xms2048m -Xmx2048m -XX:MaxMetaspaceSize=2048m -verbose:gc -XX:+HeapDumpOnOutOfMemoryError -cp "%BASEDIR%/classes:%BASEDIR%/lib/*:." sg.com.agoda.downloader.DownloadApp $* 
