Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any

    environment {
        AAA = 'BBB'
    }

    stages {
        stage('Build') {
            steps {
                sh 'echo "Hello World"'
                sh '''
                    cd myEntity
                    mvn package
                '''
            }
        }
    }
}
