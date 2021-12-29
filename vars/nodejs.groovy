def call() {

    pipeline {
        agent {
            label 'workstation'
        }
        triggers {
            pollSCM('*/2 * * * *')
        }
        stages {
            stage('One-Sequential') {
                steps {
                    sh 'sleep 45'
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
                        }
                    }

                }
            }

        }
    }

}
