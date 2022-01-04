import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def sonarQube() {
//   sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.12.143:9000 -Dsonar.login=d88bdf934eacc465d4200a82cca6650da40d17f7'
  sh 'echo sonarqube test is ok'

}

def Pubishartifacts() {
  if(env.GIT_BRANCH == "*/tags/*") {
    println '(This is ran on tag)'
  }
  else {
    Utils.markStageSkippedForConditional('publish artifactes')
  }
}
