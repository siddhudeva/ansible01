def call() {
    pipeline {
        agent {
            label '${BUILD_LABEL}'
        }
        triggers {
            pollSCM('*/2 * * * *')
        }
        stages {
            stage('One-Sequential') {
                steps {
                    sh 'echo ${COMPONENT} is success'
                }
            }
            stage('Two-Parallel') {
                parallel {

                    stage('Two1') {
                        steps {
                            sh 'sleep 60'
                        }
                    }
                    stage('Two2') {
                        steps {
                            sh 'sleep 90'
                            sh 'echo ${BUILD_LABEL}'
                        }
                    }

                }
            }

        }
    }

}
