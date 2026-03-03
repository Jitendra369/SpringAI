pipeline{
    agent any

    tools {
    maven 'Maven'
    jdk 'java_17'
}

    stages{
//        stage('Checkout') {
//            steps {
//                checkout scm
//            }
//        }

        stage('Build Maven') {
            steps {
               bat 'java -version'
               bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'doccker build -t aiservice:1.0 .'
            }

        }

        stage('Deploy Container') {
            steps {
                bat 'docker compose down || exit 0'
                bat 'docker compose up -d --build'
            }

        }
    }
}