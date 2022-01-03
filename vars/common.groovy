def sonarQube() {
   sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.12.143:9000 -Dsonar.login=d88bdf934eacc465d4200a82cca6650da40d17f7'

}