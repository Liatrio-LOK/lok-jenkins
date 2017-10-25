## lok-jenkins default config maps

### init.groovy.d

This is a collection of groovy scripts that executed after jenkins is initialized.
They do things like installing plugins, configuring jenkins, and creating seedjobs.

Create a `jenkins-init` configmap that will be mounted to the 
$JENKINS_HOME/groovy.init.d directory of the jenkins container at runtime.

`kubectl create configmap jenkins-init --from-file='./groovy.init.d'`

### jenkins-credentials

Default credentials to boot jenkins with. This should only matter in local
environments, public-facing environments should have security configured with
a groovy init script.

`kubectl create configmap jenkins-credentials --from-literal=user.name=admin --from-literal=user.password=admin123`

