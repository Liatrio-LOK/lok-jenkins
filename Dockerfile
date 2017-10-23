FROM jenkins/jenkins:lts

MAINTAINER "lok@liatrio.com"

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY resources/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

