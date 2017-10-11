FROM jenkins/jenkins:2.83

MAINTAINER "lok@liatrio.com"

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
