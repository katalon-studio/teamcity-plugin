pipeline {
    agent none
    stages {

        stage ('Build') {
            agent any

            steps {
               sh 'mvn clean package'
            }

            post {
                always {
                    archiveArtifacts artifacts: 'target/*', onlyIfSuccessful: true
                }
            }
        }

    }
}
