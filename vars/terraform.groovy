def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
        parameters {
            choice(name: 'ENVIRONMENT', choices: ['', 'dev', 'prod'], description: 'Pick environment')
            choice(name: 'ACTION', choices: ['', 'apply', 'destroy'], description: 'Pick terraform action')

        }

//    triggers {
//      pollSCM('H/2 * * * *')
//    }


        stages {

            stage('Label Builds') {
                steps {
                    script {
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ENVIRONMENT}"
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ACTION}"

                    }
                }
            }


            stage('apply terraform actions') {
                steps {
                    sh '''
                     terraform init -backend-config=env/${ENVIRONMENT}-backend.tfvars
                     terraform ${ACTION} -var-file=env/${ENVIRONMENT}.tfvars
                   '''
                }
            }
        }
//
//            stage('Lint Checks') {
//                steps {
//                    sh 'echo Lint Cases'
//                }
//            }
//            stage('Test Cases') {
//                steps {
//                    sh 'echo Test Cases'
//                    sh 'env'
//                }
//            }
//            stage('preparing artifacts') {
//                steps{
//                    script{
//                        common.preparingArtifacts()
//                    }
//                }
//            }
//
//            stage('Publish Artifacts') {
//                when {
//                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
//                }
//                steps {
//                    script {
//                        common.publishArtifacts()
//                    }
//                }
//            }
//        }
        post {
            always {
                cleanWs()
            }
        }
    }

}