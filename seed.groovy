//freeStyleJob('Roboshop') {
//    logRotator(-1, 10)
//    jdk('Java 8')
//    scm {
//        github('jenkinsci/job-dsl-plugin', 'master')
//    }
//    triggers {
//        githubPush()
//    }
//    steps {
//        gradle('clean build')
//    }
//    publishers {
//        archiveArtifacts('job-dsl-plugin/build/libs/job-dsl.hpi')
//    }
//}
//folder('CI-pipeline') {
//    displayName('CI-pipeline')
//    description('CI-pipeline')
//}
////def COMPONENTS = ["cart", "catalogue", "payment", "shipping", "user", "dispatch"]
////def SIZE =  COMPONENTS.size -1
////for( i in 0..SIZE) {
////    def j = COMPONENTS[i]
//    pipelineJob('CI-pipeline/cart') {
//        configure { flowdefinition ->
//            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
//                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
//                    'userRemoteConfigs' {
//                        'hudson.plugins.git.UserRemoteConfig' {
//                            'url'('https://github.com/siddhudeva/cart.git')
//                        }
//                    }
//                    'branches' {
//                        'hudson.plugins.git.BranchSpec' {
//                            'name'('*/main')
//                        }
//                    }
//                }
//                'scriptPath'('Jenkinsfile')
//                'lightweight'(true)
//            }
//        }
//    }
////}


pipelineJob('roboshop-ansible') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/raghudevopsb61/ansible.git')
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}

folder('CI-Pipelines') {
    displayName('CI-Pipelines')
    description('CI-Pipelines')
}

def COMPONENTS = ["cart", "catalogue", "payment", "shipping", "user", "dispatch"]

def SIZE =  COMPONENTS.size -1

for(i in 0..SIZE) {
    def j = COMPONENTS[i]
    pipelineJob("CI-Pipelines/${j}") {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'("https://github.com/raghudevopsb61/${j}.git")
                        }
                    }
                    'branches' {
                        'hudson.plugins.git.BranchSpec' {
                            'name'('*/main')
                        }
                    }
                }
                'scriptPath'('Jenkinsfile')
                'lightweight'(true)
            }
        }
    }
}