FROM centos

RUN sed -i -e "s|mirrorlist=|#mirrorlist=|g" /etc/yum.repos.d/CentOS-*
RUN sed -i -e "s|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g" /etc/yum.repos.d/CentOS-*
RUN yum install -y net-tools telnet wget vim

RUN echo "alias ll='ls -al'" >> /etc/profile

RUN mkdir -p /install/java
RUN cd /install/java && wget https://download.java.net/java/GA/jdk22.0.1/c7ec1332f7bb44aeba2eb341ae18aca4/8/GPL/openjdk-22.0.1_linux-x64_bin.tar.gz
RUN cd /install/java && tar zxvf openjdk-22.0.1_linux-x64_bin.tar.gz
RUN echo 'export JAVA_HOME=/install/java/jdk-22.0.1' >> /etc/profile
RUN echo 'export PATH=$JAVA_HOME/bin:$PATH' >> /etc/profile

RUN mkdir -p /app
COPY target/myWeb-1.0-SNAPSHOT.jar /app/myWeb-1.0-SNAPSHOT.jar

RUN echo '#!/bin/bash' >> /app/startup.sh
RUN echo 'source /etc/profile' >> /app/startup.sh
RUN echo 'exec java -jar /app/myWeb-1.0-SNAPSHOT.jar' >> /app/startup.sh
RUN chmod +x /app/startup.sh

ENTRYPOINT ["/app/startup.sh"]