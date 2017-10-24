import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

// Example init script to load a seedjob.
// Place in $JENKINS_HOME/init.groovy.d to load seedjob after jenkins init

def seedjobDsl = """
job('example-seedjob') {
    scm {
        git('https://github.com/liatrio-lok/lok-example-seedjob.git')
    }
    triggers {
        scm('H/2 * * * *')
    }
    steps {
        groovyScriptFile('**/*.groovy')
    }
}
"""

def workspace = new File('.')
def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)
new DslScriptLoader(jobManagement).runScript(seedjobDsl)

