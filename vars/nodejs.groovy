def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }

///        triggers {
//            pollSCM('H/2 * * * *')
//       }

        stages {
            stage('Label Builds') {
                steps {
                    script {
                        env.gitTag = GIT_BRANCH.split('/').last()
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
                    }
                }
            }

            stage('Deploy') {
                when { tag "release-*" }
                steps {
                    echo 'Deploying only because this commit is tagged...'
                    sh 'make deploy'
                }
            }


            stage('Check the Code Quality') {
                steps {
                    script {
                        common.sonarQube()
                    }
                }
            }
            stage('Lint checks') {
                steps {
                    echo 'lint checks'
                   sh 'echo env'
                }
            }

            stage('publish artifactes') {
                steps {
                    script{
                        common.Pubishartifacts()
                    }
                }
            }

            stage('sample') {
                steps {
                    echo 'sample'
                }

            }
        }
    }
}





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
// }


