
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


folder('immutables') {
    displayName('CI-Pipelines')
    description('CI-Pipelines')
}

def COMPONENT = ["cart", "frontend", "catalogue", "payment", "shipping", "user", "dispatch"]
def SIZEs =  COMPONENT.size -1
for(i in 0..SIZEs) {
    def j = COMPONENT[i]
    pipelineJob("immutables/cart") {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'("https://github.com/siddhudeva/cart.git")
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

    folder('Mutable') {
        displayName('Mutable')
        description('Mutable')
    }
    pipelineJob('Mutable/VPC') {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
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
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
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
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
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
//folder('Mutable') {
//    displayName('Mutable')
//    description('Mutable')
//}
        displayName('Mutable/databases')
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

    pipelineJob('Mutable/all_infra_creat_Mutable') {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
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
                'scriptPath'('Jenkinsfile_all_Mutable_infra_create')
                'lightweight'(true)
            }
        }
    }
    pipelineJob('Mutable/all_infra_Distroy') {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
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
                'scriptPath'('Jenkins_All_Mutable_distroy')
                'lightweight'(true)
            }
        }
    }
//}


/////////////////////

//pipelineJob('roboshop-ansible') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/siddhudevav/ansible.git')
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
//}
//
//folder('CI-Pipelines') {
//    displayName('CI-Pipelines')
//    description('CI-Pipelines')
//}
//
//def COMPONENTS = ["cart", "catalogue", "payment", "shipping", "user", "dispatch", "frontend"]
//
//def SIZE =  COMPONENTS.size -1
//
//for(i in 0..SIZE) {
//    def j = COMPONENTS[i]
//    pipelineJob("CI-Pipelines/${j}") {
//        configure { flowdefinition ->
//            flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
//                'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
//                    'userRemoteConfigs' {
//                        'hudson.plugins.git.UserRemoteConfig' {
//                            'url'("https://github.com/siddhudeva/${j}.git")
//                        }
//                    }
//                    'branches' {
//                        'hudson.plugins.git.BranchSpec' {
//                            'name'('*/tags/*')
//                        }
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
//}
//
//folder('Mutable') {
//    displayName('Mutable')
//    description('Mutable')
//}
//
//pipelineJob('Mutable/VPC') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/siddhudeva/terraform-vpc.git')
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
//}
//
//pipelineJob('Mutable/APP-SETUP') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/siddhudeva/terraform_LB.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkins-alb-mutable')
//            'lightweight'(true)
//        }
//    }
//}
//
//pipelineJob('Mutable/ALB') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/siddhudeva/terraform_LB.git')
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
//}
//
//pipelineJob('Mutable/DB') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/siddhudeva/terraform_databases.git')
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
//}
//
//
//pipelineJob('Mutable/All-Infra-Create') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/raghudevopsb61/ansible-1.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkinsfile-Mutable-All-in-one-Infra-Create')
//            'lightweight'(true)
//        }
//    }
//}
//
//pipelineJob('Mutable/All-Infra-Destroy') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/raghudevopsb61/jenkins.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkinsfile-Mutable-All-in-one-Infra-Destroy')
//            'lightweight'(true)
//        }
//    }
//}
//
///////////////////////
//
//folder('Immutable') {
//    displayName('Immutable')
//    description('Immutable')
//}
//
//pipelineJob('Immutable/All-Infra-Create') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/raghudevopsb61/jenkins.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkinsfile-Immutable-All-in-one-Infra-Create')
//            'lightweight'(true)
//        }
//    }
//}
//
//pipelineJob('Immutable/All-Infra-Destroy') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/raghudevopsb61/jenkins.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkinsfile-Immutable-All-in-one-Infra-Destroy')
//            'lightweight'(true)
//        }
//    }
//}
//
//pipelineJob('Immutable/App-Deploy') {
//    configure { flowdefinition ->
//        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//            'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//                'userRemoteConfigs' {
//                    'hudson.plugins.git.UserRemoteConfig' {
//                        'url'('https://github.com/raghudevopsb61/jenkins.git')
//                    }
//                }
//                'branches' {
//                    'hudson.plugins.git.BranchSpec' {
//                        'name'('*/main')
//                    }
//                }
//            }
//            'scriptPath'('Jenkinsfile-Immutable-App-Deploy')
//            'lightweight'(true)
//        }
//    }
}