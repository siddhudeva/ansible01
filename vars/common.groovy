import org.jenkinsci.plugins.pipeline.modeldefinition.Utils


def sonarQube() {
//  sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.15.251:9000 -Dsonar.login=admin -Dsonar.password=admin123'
//  sh 'sonar-quality-gate.sh admin admin123 172.31.15.251 ${COMPONENT}'
  println 'SonarQube Testing'
}

def publishArtifacts() {
//  if(env.GIT_BRANCH == "*tag*") {
//    println 'Ran on Tag'
//  } else {
//    Utils.mark  StageSkippedForConditional('Publish Artifacts')
//  }
  sh '''
curl -v -o -f -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}.${gitTag}.zip http://nexus1.roboshop.internal:8081/repository/${COMPONENT}/${COMPONENT}.${gitTag}.zip
'''

}

def preparingArtifacts() {
  if(env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "6") {
    sh '''
       npm install
       zip -r ${COMPONENT}.${gitTag}.zip node_modules server.js
       ls -ltr
'''
  }
  if(env.PROG_LANG_NAME == "java" && env.PROG_LANG_VERSION == "1.8") {
    sh'''
       mvn clean package
       mv target/${COMPONENT}-1.0.jar ${COMPONENT}.jar
         zip -r ${COMPONENT}.${gitTag}.zip ${COMPONENT}.jar

'''
  }
  if(env.PROG_LANG_NAME == "python" && env.PROG_LANG_VERSION == "3") {
    sh'''
      zip -r ${COMPONENT}.${gitTag}.zig *.py ${COMPONENT}.ini requirements.txt
'''
  }
  if(env.PROG_LANG_NAME == "golang" && env.PROG_LANG_VERSION == "1.5") {
    sh '''
      go mod init dispatch
      go get 
      go build 
      zip -r ${COMPONENT}.${gitTag}.zip ${COMPONENT}
'''
  }
  if(env.PROG_LANG_NAME == "angular") {
    sh '''
      cd static
      zip -r ../${COMPONENT}-${gitTag}.zip * 
'''
  }

}




















//def prepareArtifacts() {
//  if(env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "6") {
//    sh '''
//      npm install
//      zip -r ${COMPONENT}-${gitTag}.zip node_modules server.js
//      ls -ltr
//    '''
//  }
//  // Java
//  // Python
//  // Golang
//}


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
//    println 'this is ran ons tag'
//  }
//  else {
//    Utils.markStageSkippedForConditional('publish artifactes')
// }
//}
//