FROM jenkins/jenkins:2.83

MAINTAINER "lok@liatrio.com"

ENV ADMIN_USERNAME="admin" \
    ADMIN_PASSWORD="admin123" \
    JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY resources/plugins.txt /usr/share/jenkins/ref/plugins.txt
#COPY resources/config.xml /usr/share/jenkins/ref/config.xml
#COPY resources/jobs /usr/share/jenkins/ref/jobs/
#COPY resources/plugins.txt /usr/share/jenkins/ref/
#COPY resources/security-plugins.txt /usr/share/jenkins/ref/
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/ref/plugins.txt 
#COPY /resources/security.groovy /usr/share/jenkins/ref/security.groovy
#COPY resources/init.groovy.d/install.groovy /usr/share/jenkins/ref/init.groovy.d/install.groovy
COPY resources/init.groovy.d/1security.groovy /usr/share/jenkins/ref/init.groovy.d/1security.groovy
COPY resources/init.groovy.d/2security.groovy /usr/share/jenkins/ref/init.groovy.d/2security.groovy
#COPY resources/init.groovy.d/install.groovy /usr/share/jenkins/ref/init.groovy.d/install.groovy
