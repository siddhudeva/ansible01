def sonarQube() {
    sonar-scanner '-Dsonar.projectKey=cart -Dsonar.sources=. ' '-Dsonar.host.url=http://3.239.17.117:9000' '-Dsonar.login=3f022df13daad97bcdcc86e09c6f5da415a512d9'
}