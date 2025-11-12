pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Skipping tests for now (DB dependent)'
            }
        }

        stage('Package') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
