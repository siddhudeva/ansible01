import org.jenkinsci.plugins.pipeline.modeldefinition.Utils


def sonarQube() {
//  sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.15.251:9000 -Dsonar.login=admin -Dsonar.password=admin123'
//  sh 'sonar-quality-gate.sh admin admin123 172.31.15.251 ${COMPONENT}'
  println 'SonarQube Testing'
}

def publishArtifacts() {
  if(env.GIT_BRANCH == "*tag*") {
    println 'Ran on Tag'
  } else {
    Utils.markStageSkippedForConditional('Publish Artifacts')
  }
//  sh '''
//    curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${gitTag}.zip http://nexus.roboshop.internal:8081/repository/${COMPONENT}/${COMPONENT}-${gitTag}.zip
//  '''
}

def prepareArtifacts() {
  if(env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "6") {
    sh '''
      npm install 
      zip -r ${COMPONENT}-${gitTag}.zip node_modules server.js
      ls -ltr
    '''
  }
  // Java
  // Python
  // Golang
}


////import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
//
//def Publish() {
//  echo 'This is working fine'
//
//}



//def sonarQube() {
//   sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.12.143:9000 -Dsonar.login=d88bdf934eacc465d4200a82cca6650da40d17f7'
//  sh 'echo sonarqube test is ok'
//}

//def prepareArtifacts() {
//  if(env.GIT_BRANCH == "*tag*") {
//    echo 'This is ran by tag'
//  } else {
//    Utils.markStageSkippedForConditional('Publish Artifacts')
//  }

//  echo 'it worked'
//}

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
// }
//}