FROM jenkins/jenkins:2.83

MAINTAINER "lok@liatrio.com"

ENV ADMIN_USERNAME="admin" \
    ADMIN_PASSWORD="admin123" \
    JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY resources/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/ref/plugins.txt 

COPY resources/init.groovy.d/1security.groovy /usr/share/jenkins/ref/init.groovy.d/_security.groovy
COPY resources/init.groovy.d/2security.groovy /usr/share/jenkins/ref/init.groovy.d/security.groovy

