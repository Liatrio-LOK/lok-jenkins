# lok-jenkins

[![Build Status](https://travis-ci.org/Liatrio-LOK/lok-jenkins.svg?branch=master)](https://travis-ci.org/Liatrio-LOK/lok-jenkins)

LOK-jenkins contains the resources required to get Jenkins running on Kubernetes for the LOK project.

## Launching on a Cluster

0. If using minikube, launch the cluster with extra memory (default 1GB isn't enough)
  * `minikube start --memory 8192`

1. Create config maps 
  * `kubectl create configmap jenkins-init --from-file='configmaps/default/init.groovy.d/'`
  * `kubectl create configmap jenkins-credentials --from-literal=user.name=admin --from-literal=user.password=admin123`

2. Create persistent volume(s)
  * `kubectl create -f volumes/`

3. Create deployment
  * `kubectl create -f deployments/`

4. Create service
  * `kubectl create -f services/`

5. (Minikube only) Expose service through minikube
  * `minikube service jenkins`

