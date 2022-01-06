def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }

//    triggers {
//      pollSCM('H/2 * * * *')
//    }
        environment {
            PROG_LANG_NAME = "python"
            PROG_LANG_VERSION = "3"
            NEXUS = credentials('NEXUS')
        }

        stages {

            stage('Label Builds') {
                steps {
                    script {
                        env.gitTag = GIT_BRANCH.split('/').last()
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
                    }
                }
            }


            stage('Check the Code Quality') {
                steps {
                    script {
                        common.sonarQube()
                    }
                }
            }

            stage('Lint Checks') {
                steps {
                    sh 'echo Lint Cases'
                }
            }
            stage('Test Cases') {
                steps {
                    sh 'echo Test Cases'
                    sh 'env'
                }
            }
            stage('preparing artifacts') {
                steps{
                    script{
                        common.preparingArtifacts()
                    }
                }
            }

            stage('Publish Artifacts') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
                }
                steps {
                    script {
                        common.publishArtifacts()
                    }
                }
            }
        }

        post {
            always {
                cleanWs()
            }
        }
    }
}




//def call() {
//    pipeline {
//        agent {
//            label '${BUILD_LABEL}'
//        }
//        triggers {
//            pollSCM('*/2 * * * *')
//        }
//        stages {
//            stage('One-Sequential') {
///               steps {
//                    sh 'echo ${COMPONENT} is success'
//                }
//            }
//            stage('Two-Parallel') {
//                parallel {
//
//                    stage('Two1') {
//                        steps {
//                            sh 'sleep 60'
//                        }
//                    }
//                    stage('Two2') {
//                        steps {
//                            sh 'sleep 90'
//                            sh 'echo ${BUILD_LABEL}'
//                        }
//                    }
//
//                }
//            }
//
//        }
//    }
//
//}
