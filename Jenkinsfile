pipeline {
    agent any
    environment {
        DOCKER_USER = 'sudhirkh4'
        IMAGE_NAME = "${DOCKER_USER}/calculator-api"
        IMAGE_TAG = "${env.BUILD_NUMBER}"

        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials-id') {
                        def customImage = docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
                        customImage.push()
                        customImage.push('latest')
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'BUILD_NUMBER=${BUILD_NUMBER} DOCKER_USER=${DOCKER_USER} docker compose up -d'
            }
        }
    }
    post {
        success {
            emailext subject: "Success: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                     body: "Build successful.",
                     to: "Kh.Sudhir@iiitb.ac.in"
        }
        failure {
            emailext subject: "Failed: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                     body: "Build failed. Check Jenkins logs.",
                     to: "Kh.Sudhir@iiitb.ac.in"
        }
    }
}

//Added a comment.