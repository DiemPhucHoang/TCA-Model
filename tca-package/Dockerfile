FROM java:8
MAINTAINER phuc

ENV http_proxy 10.10.10.10:8080
ENV https_proxy 10.10.10.10:8080

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

ENV KARAF_VERSION=4.2.8

COPY bin/apache-karaf-${KARAF_VERSION}.tar.gz ./

RUN mkdir /opt/karaf; \
    tar --strip-components=1 -C /opt/karaf -xzf apache-karaf-${KARAF_VERSION}.tar.gz; \
    rm -rf /opt/karaf/examples \
    rm apache-karaf-${KARAF_VERSION}.tar.gz;

# COPY bin/lib /opt/lib
#COPY bin/startServer.sh /opt/
#RUN chmod 755 /opt/startServer.sh
#COPY target/tca-features-1.0-SNAPSHOT.kar /opt/karaf/deploy
#EXPOSE 1099 8101 44444 5005 8181
#ENTRYPOINT ["/opt/startServer.sh"]

COPY bin/etc/org.ops4j.datasource-tca.cfg /opt/karaf/etc
COPY target/tca-features-1.0-SNAPSHOT.kar /opt/karaf/deploy
EXPOSE 1099 8101 44444 5005 8181
ENTRYPOINT ["/opt/karaf/bin/karaf"]