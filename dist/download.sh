#!/bin/bash
export JAVA_HOME=/usr/
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=$JAVA_HOME/jre/lib/ext:$JAVA_HOME/lib/tools.jar

BASEDIR=$(dirname "$0")
$JAVA_HOME/bin/java -server -Xms2048m -Xmx2048m -XX:MaxMetaspaceSize=2048m -verbose:gc -XX:+HeapDumpOnOutOfMemoryError -cp "$BASEDIR/classes:$BASEDIR/lib/*:." sg.com.agoda.downloader.DownloadApp $* 
