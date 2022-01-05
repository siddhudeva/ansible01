def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }

//    triggers {
//      pollSCM('H/2 * * * *')
//    }

//        environment {
//            PROG_LANG_NAME = "nodejs"
//            PROG_LANG_VERSION = "6"
//            NEXUS = credentials('NEXUS')
//        }

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

            stage('Publish Artifacts') {
//                when {
//                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
//                }
                steps {
                    script {
//                        common.prepareArtifacts()
                        common.publishArtifacts()
                    }
                }
            }
//            post {
//                always {
//                    cleanWs()
//                }
//            }

        }
    }
}

//def call() {
//    pipeline{
//      agent {
//         label "BUILD_LABEL"
//      }
//      stages{
//         stage('lint checks') {
//             steps{
//                 echo 'lint checks'
//             }
//         }
//         stage('check code quality') {
//             steps{
//                 echo 'check quality of code using sonarqube'
//             }
//         }
//         stage('preparing artifacats') {
//             steps{
//                 echo 'preparing artifacts'
//             }
//         }
//
////         stage('publish artifacts') {
////             steps{
////                 stages{
////                     common.Publish()
////                 }
////             }
////         }
//         post{
//             always{
//                 cleanWs
//             }
//         }
//      }
//    }
//}
//
//
//


//def call() {
//    pipeline {
//        agent {
//            label "${BUILD_LABEL}"
//        }
//
/////        triggers {
////            pollSCM('H/2 * * * *')
////       }
//
//        stages {
//            stage('Label Builds') {
//                steps {
//                    script {
//                        env.gitTag = GIT_BRANCH.split('/').last()
//                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
//                    }
//                }
//            }
//
//            stage('Deploy') {
//                when { tag "release-*" }
//                steps {
//                    echo 'Deploying only because this commit is tagged...'
//                    sh  'make deploy'
//                }
//            }
//
//
//            stage('Check the Code Quality') {
//                steps {
//                    script {
//                        common.sonarQube()
//                    }
//                }
//            }
//            stage('Lint checks') {
//                steps {
//                    echo 'lint checks'
//                   sh 'echo env'
//                }
//            }
//
//            stage('publish artifactes') {
//                steps {
//                    script{
//                        common.Pubishartifacts()
//                    }
//                }
//            }
//
//            stage('sample') {
//                steps {
//                    echo 'sample'
//                }
//
//            }
//        }
//    }
//}
//
//


//            stage('Label Build') {
//                steps {
//                    script {
//                        def gitTag = GIT_BRANCH.split('/').last()
//                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
//                    }
//                }
//            }

//def call() {
//    pipeline {
//        agent {
//            label "${BUILD_LABEL}"
//        }
////        stages {
////            stage() {
////                steps {
////                    sh 'echo ${COMPONENT} is success'
////                }
////            }
////            stage() {
////                steps {
////                    script{
////                        common.sonarQube()
////                    }
////                }
////            }
////        }
////    }
////}
//
//        stages {
//
//
//            stage('Check the Code Quality') {
//                steps {
//                    script {
//                        common.sonarQube()
//                    }
//                }
//            }
//
////            stage('Lint Check') {
////                steps {
////                    sh 'echo Lint Cases'
////                    sh 'echo lint '
////                }
////            }
////
////            stage('Test Cases') {
////                steps {
////                    sh 'echo Test Cases'
////                }
////            }
////
////            stage('Publish Artifacts') {
////                steps {
////                    sh 'echo Publish Artifacts'
////                }
////            }
////
////        }
//
//            post {
//                always {
//                    cleanWs()
//                }
//            }
//
//        }
//    }
// }}

