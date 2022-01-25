
folder('CI-Pipelines') {
    displayName('CI-Pipelines')
    description('CI-Pipelines')
}

def COMPONENTS = ["cart", "frontend", "catalogue", "payment", "shipping", "user", "dispatch"]

def SIZE =  COMPONENTS.size -1

for(i in 0..SIZE) {
    def j = COMPONENTS[i]
    pipelineJob("CI-Pipelines/${j}") {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'("https://github.com/siddhudeva/${j}.git")
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




////freeStyleJob('Roboshop') {
////    logRotator(-1, 10)
////    jdk('Java 8')
////    scm {
////        github('jenkinsci/job-dsl-plugin', 'master')
////    }
////    triggers {
////        githubPush()
////    }
////    steps {
////        gradle('clean build')
////    }
////    publishers {
////        archiveArtifacts('job-dsl-plugin/build/libs/job-dsl.hpi')
////    }
////}
////folder('CI-pipeline') {
////    displayName('CI-pipeline')
////    description('CI-pipeline')
////}
//////def COMPONENTS = ["cart", "catalogue", "payment", "shipping", "user", "dispatch"]
//////def SIZE =  COMPONENTS.size -1
//////for( i in 0..SIZE) {
//////    def j = COMPONENTS[i]
////    pipelineJob('CI-pipeline/cart') {
////        configure { flowdefinition ->
////            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
////                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
////                    'userRemoteConfigs' {
////                        'hudson.plugins.git.UserRemoteConfig' {
////                            'url'('https://github.com/siddhudeva/cart.git')
////                        }
////                    }
////                    'branches' {
////                        'hudson.plugins.git.BranchSpec' {
////                            'name'('*/main')
////                        }
////                    }
////                }
//pipelineJob('roboshop-ansible') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/siddhudeva/cart.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkinsfile')
//            'lightweight'(true)
//        }
//    }
//
//}
//                'scriptPath'('Jenkinsfile')
//                'lightweight'(true)
//            }
//        }
//    }
//}


folder('Mutable') {
    displayName('Mutable')
    description('Mutable')
}
pipelineJob('Mutable/VPC') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/siddhudeva/terraform-vpc.git')
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


pipelineJob('Mutable/APP-SETUP') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/siddhudeva/ansible01.git')
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkins-Mutablefile')
            'lightweight'(true)
        }
    }
}

pipelineJob('Mutable/APP-ALB') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'('https://github.com/siddhudeva/terraform_LB.git')
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkins-alb-mutable')
            'lightweight'(true)
        }
    }
}


folder('VPC') {
    displayName('VPC')
    description('DATABASES AND VPC')
    pipelineJob('VPC/Terraform-Databases') {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'('https://github.com/siddhudeva/terraform_databases.git')
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
