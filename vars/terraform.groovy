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
            stage('Print variables') {
                steps {
                sh 'echo ${ENVIRONMENT}'
                sh 'echo ${ACTION}'
                }
            }


            stage('apply terraform actions') {
                steps {
                    sh '''
                     terraform init -backend-config=env/${ENVIRONMENT}-backend.tfvars
                     terraform ${ACTION} -auto-approve -var-file=env/${ENVIRONMENT}.tfvars
                   '''
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