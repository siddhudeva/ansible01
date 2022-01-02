def sonarQube() {
    sh 'sh sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://3.239.17.117:9000 -Dsonar.login=admin -Dsonar.password=admin123'
//            '  sh \'sonar-quality-gate.sh admin admin123 172.31.12.143 ${COMPONENT}\''
}