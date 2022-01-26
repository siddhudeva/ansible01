def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
        options {
            ansiColor('xterm')
        }

        parameters {
            choice(name: 'ENVIRONMENT', choices: ['', 'dev', 'prod'], description: 'Pick environment')
            choice(name: 'ACTION', choices: ['', 'apply', 'destroy'], description: 'Pick action to perform')

        }
        stages {
            stage('Label Builds') {
                steps {
                    script {
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ENVIRONMENT}"
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ACTION}"

                    }
                }
            }
            stage('Apply terraform actions') {
                steps {
                    script {
                        sh '''
                           terraform init -backend-config=env/${ENVIRONMENT}-backend.tfvars  
//                           terraform ${ACTION} -auto-approve -var-file=env/${ENVIRONMENT}.tfvars
                       '''
                    }
                }
            }
        }
//        post {
//            always {
//                cleanWs()
//
//            }
//        }
    }

}
