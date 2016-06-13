# java-downloader

~~~~ 
        URL website = new URL("http://buildlogs.centos.org/rolling/7/isos/x86_64/CentOS-7-x86_64-DVD.iso");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("CentOS-7-x86_64-DVD.iso");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
~~~~         
        
