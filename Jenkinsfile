pipeline{
    agent any

    tools {
    maven 'Maven'
}

    stages{
//        stage('Checkout') {
//            steps {
//                checkout scm
//            }
//        }

        stage('Build Maven') {
            steps {
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