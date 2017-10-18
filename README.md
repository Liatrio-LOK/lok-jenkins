# lok-jenkins

[![Build Status](https://travis-ci.org/Liatrio-LOK/lok-jenkins.svg?branch=master)](https://travis-ci.org/Liatrio-LOK/lok-jenkins)

LOK-jenkins contains the resources required to get Jenkins running on Kubernetes for the LOK project.


## Set up Configmaps

The `jenkins-credentials` configMap is intended to be created from the console using the following command:

```
kubectl create configmap jenkins-credentials --from-literal=user.name=<username> --from-literal=user.password=<password>
```

The `jenkin-security` configMap is intended to be created from the console with the following command:

```
kubectl create configmap jenkins-security --from-file={path to this dir}/resources/init.groovy.d/
```

## Launching on a Cluster

1. Create persistent volume(s): `kubectl create -f volumes/`

2. Create deployment: `kubectl create -f deployments/`

3. Create service: `kubectl create -f services/`

4. (Minikube only) Expose service through minikube: `minikube service jenkins`

