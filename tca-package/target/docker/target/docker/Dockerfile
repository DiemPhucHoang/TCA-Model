FROM java:8
MAINTAINER phuc
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

ENV KARAF_VERSION=4.2.8

COPY apache-karaf-${KARAF_VERSION}.tar.gz ./

RUN mkdir /opt/karaf; \
    tar --strip-components=1 -C /opt/karaf -xzf apache-karaf-${KARAF_VERSION}.tar.gz; \
    rm -rf /opt/karaf/examples \
    rm apache-karaf-${KARAF_VERSION}.tar.gz;

COPY bin/etc/org.ops4j.datasource-tca.cfg /opt/karaf/etc

COPY ../tca-features/target/tca-features-1.0-SNAPSHOT.kar /opt/karaf/deploy

EXPOSE 1099 8101 44444
ENTRYPOINT ["/opt/karaf/bin/karaf"]