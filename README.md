# lok-jenkins

[![Build Status](https://travis-ci.org/Liatrio-LOK/lok-jenkins.svg?branch=master)](https://travis-ci.org/Liatrio-LOK/lok-jenkins)

LOK-jenkins contains the resources required to get jenkins running on kubernetes for the LOK project. 


## Setting up configmaps:

The `jenkins-credentials` configMap is intended to be created from the console using the following command:
kubectl create configmap jenkins-credentials --from-literal=user.name=`<username>` --from-literal=user.password=`<password>`

The `jenkin-security` configMap is intended to be created from the console with the following command:
kubectl create configmap jenkins-security --from-file={path to this dir}/resources/init.groovy.d/
