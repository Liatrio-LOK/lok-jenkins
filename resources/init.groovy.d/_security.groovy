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