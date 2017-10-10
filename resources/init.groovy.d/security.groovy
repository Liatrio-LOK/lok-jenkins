#!groovy

import hudson.model.*
import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule
import com.cloudbees.plugins.credentials.*


/*
* This code was grabbed from a script located at: 
* https://github.com/blacklabelops/jenkins/blob/master/imagescripts/initplugins.sh
*/
//Installs security plugins at runtime
def installed = false
def initialized = false
def pluginParameter="matrix-auth" //space separated list of plugins
def plugins = pluginParameter.split()
def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()
plugins.each {
  if (!pm.getPlugin(it)) {
    if (!initialized) {
      uc.updateAllSites()
      initialized = true
    }
    def plugin = uc.getPlugin(it)
    if (plugin) {
    	def installFuture = plugin.deploy()
      while(!installFuture.isDone()) {
        sleep(3000)
      }
      installed = true
    }
  }
}
if (installed) {
  instance.save()
  instance.restart()
}
// This is the security set up 
//Jenkins.instance.updateCenter.getPlugin("matrix-auth:1.7").deploy()
def proc ='/usr/share/jenkins/ref/security.groovy'.execute()
/*
def instance = Jenkins.getInstance()

def env = System.getenv()
def username = env['ADMIN_USERNAME']
def password = env['ADMIN_PASSWORD']

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(username, password)
instance.setSecurityRealm(hudsonRealm)

def strategy = new GlobalMatrixAuthorizationStrategy()
strategy.add(Jenkins.ADMINISTER, username)
instance.setAuthorizationStrategy(strategy)

instance.save()
 */
/*
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount(username, password)
instance.setSecurityRealm(hudsonRealm)
 
def matrixStrategy = new GlobalMatrixAuthorizationStrategy()

// Jenkins User
matrixStrategy.add(hudson.model.Item.BUILD, 'jenkins')

// Anonoymous Permissions
matrixStrategy.add(Jenkins.READ, 'anonymous')
matrixStrategy.add(hudson.model.Item.READ, 'anonymous')
matrixStrategy.add(hudson.model.View.READ, 'anonymous')

// General Permissions
matrixStrategy.add(Jenkins.ADMINISTER, username)
matrixStrategy.add(Jenkins.RUN_SCRIPTS, username)
matrixStrategy.add(Jenkins.READ, username)

// Job Permissions
matrixStrategy.add(hudson.model.Item.BUILD, username)
matrixStrategy.add(hudson.model.Item.CANCEL, username)
matrixStrategy.add(hudson.model.Item.CONFIGURE, username)
matrixStrategy.add(hudson.model.Item.CREATE, username)
matrixStrategy.add(hudson.model.Item.DELETE, username)
matrixStrategy.add(hudson.model.Item.DISCOVER, username)
matrixStrategy.add(hudson.model.Item.EXTENDED_READ, username)
matrixStrategy.add(hudson.model.Item.READ, username)
matrixStrategy.add(hudson.model.Item.WIPEOUT, username)
matrixStrategy.add(hudson.model.Item.WORKSPACE, username)

// View Permissions
matrixStrategy.add(hudson.model.View.CONFIGURE, username)
matrixStrategy.add(hudson.model.View.CREATE, username)
matrixStrategy.add(hudson.model.View.DELETE, username)
matrixStrategy.add(hudson.model.View.READ, username)

// Run Permissions
matrixStrategy.add(hudson.model.Run.DELETE, username)
matrixStrategy.add(hudson.model.Run.UPDATE, username)
matrixStrategy.add(hudson.model.Run.ARTIFACTS, username)

// Misc. Permissions
matrixStrategy.add(hudson.scm.SCM.TAG, username)
matrixStrategy.add(hudson.PluginManager.UPLOAD_PLUGINS, username)
matrixStrategy.add(hudson.PlguinManager.CONFIGURE_UPDATECENTER, username)

instance.setAuthorizationStrategy(matrixStrategy)
instance.save()
*/
