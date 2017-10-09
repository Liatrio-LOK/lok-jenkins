FROM jenkins/jenkins:2.83

MAINTAINER "lok@liatrio.com"

ENV ADMIN_USERNAME="admin" \
    ADMIN_PASSWORD="admin123" \
    JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

#COPY resources/plugins.txt /usr/share/jenkins/ref/plugins
#COPY resources/config.xml /usr/share/jenkins/ref/config.xml
#COPY resources/jobs /usr/share/jenkins/ref/jobs/
COPY resources/init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/
