def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
//        stages {
//            stage() {
//                steps {
//                    sh 'echo ${COMPONENT} is success'
//                }
//            }
//            stage() {
//                steps {
//                    script{
//                        common.sonarQube()
//                    }
//                }
//            }
//        }
//    }
//}

        stages {


            stage('Check the Code Quality') {
                steps {
                    script {
                        common.sonarQube()
                        echo sonar
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
                }
            }

            stage('Publish Artifacts') {
                steps {
                    sh 'echo Publish Artifacts'
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