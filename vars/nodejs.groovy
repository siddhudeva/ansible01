def call() {
    pipeline {
        agent {
            label $ '{BUILD_LABEL}'
        }
        triggers {
            pollSCM('H/2 * * * *')
        }
        stages {
            stage() {
                steps {
                    sh 'echo ${COMPONENT} is success'
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

    }
}