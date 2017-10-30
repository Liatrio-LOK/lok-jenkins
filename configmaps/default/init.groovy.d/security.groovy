#!groovy

import hudson.model.*
import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule
import com.cloudbees.plugins.credentials.*

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
instance.setSlaveAgentPort(8081)

instance.save()
