import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

def instance = Jenkins.getInstance()

def k = new KubernetesCloud(
  'kubernetes',
   null,
  'https://kubernetes.default.svc.cluster.local/',
  'default',
  'http://jenkins:80/',
  '10', 0, 0, 5
)
k.setSkipTlsVerify(true)

instance.clouds.replace(k)
instance.save()
