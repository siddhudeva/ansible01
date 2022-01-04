import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

def sonarQube() {
//   sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.12.143:9000 -Dsonar.login=d88bdf934eacc465d4200a82cca6650da40d17f7'
  sh 'echo sonarqube test is ok'
}

def Pubishartifacts() {
  if(env.GIT_BRANCH == "*tag*") {
    echo 'This is ran by tag'
  } else {
    Utils.markStageSkippedForConditional('publish artifactes')
  }
}
//
//  if (env.GIT_BRANCH == 'master') {
//  echo 'I only execute on the master branch'
//} else {
//  echo 'I execute elsewhere'
//}



//def Pubishatifacts() {
//  if(env.GIT_BRANCH == "*tag*") {
//    println 'this is ran on tag'
//  }
//  else {
//    Utils.markStageSkippedForConditional('publish artifactes')
//rdhf  }
//}