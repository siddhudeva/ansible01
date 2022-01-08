def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }

//    triggers {
//      pollSCM('H/2 * * * *')
//    }
        environment {
            PROG_LANG_NAME = "golang"
            PROG_LANG_VERSION = "1.15"
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
                }
            }
            stage('preparing artifacts') {
                steps {
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

//        post {
//            always {
//                cleanWs()
//
//                }
//            }
        }
    }


