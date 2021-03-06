import org.jenkinsci.plugins.pipeline.modeldefinition.Utils


def sonarQube() {
//  sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.15.251:9000 -Dsonar.login=admin -Dsonar.password=admin123'
//  sh 'sonar-quality-gate.sh admin admin123 172.31.15.251 ${COMPONENT}'
  println 'SonarQube Testing'
}
//  if(env.GIT_BRANCH == "*tag*") {
//    println 'Ran on Tag'
//  } else {
//    Utils.mark  StageSkippedForConditional('Publish Artifacts')
//  }
//  sh '''
//  Note : earlier in mutable approach we use below curl command to create and publish artificates and in that -f is enabled which is used to stop failure of publishing
// curl -v -f -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}.${gitTag}.zip http://44.202.166.237:8081/repository/${COMPONENT}/${COMPONENT}.${gitTag}.zip
//    pwd
def publishArtifacts() {
sh '''
    curl -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}.${gitTag}.zip http://nexus.roboshop.internal:8081/repository/${COMPONENT}/${COMPONENT}.${gitTag}.zip
'''

}
def makeAMI() {
  sh '''
pwd
//    terraform init
//    terraform plan -var.APP_VERSION=${gitTag}
//    terraform apply -auto-approve -var.APP_VERSION=${gitTag}
//    terraform state rm module.cart-ami.aws_ami_from_instance.ami
//    terraform destroy -auto-approve -var.APP_VERSION=${gitTag}
'''

}

def preparingArtifacts() {
  if(env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "6") {
    sh '''

       sudo npm install
       zip -r ${COMPONENT}.${gitTag}.zip node_modules server.js
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
      ls -ltr
      zip -r ${COMPONENT}.${gitTag}.zip *.py ${COMPONENT}.ini requirements.txt
      ls -ltr
      pwd
'''
  }
  if(env.PROG_LANG_NAME == "golang" && env.PROG_LANG_VERSION == "1.15") {
    sh '''
      go mod init dispatch
      go get 
      go build 
      zip -r ${COMPONENT}.${gitTag}.zip ${COMPONENT}
      ls -ltr
      pwd
'''
  }
  if(env.PROG_LANG_NAME == "angular") {
    sh '''
      cd static
      sudo zip -r ../${COMPONENT}.${gitTag}.zip * 
      ls -ltr
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