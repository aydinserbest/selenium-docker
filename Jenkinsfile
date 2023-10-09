pipeline{
    agent any
    stages{
        stage('build jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){
            steps{
                sh 'docker build -t=aserbest/selenium:latest .'  //aserbest dockerhub account username

            }
        }
         stage('push image'){
         environment{
            DOCKER_HUB=credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push aserbest/selenium:latest'
                sh "docker tag aserbest/selenium:latest aserbest/selenium:${env.BUILD_NUMBER}"
                sh "docker push aserbest/selenium:${env.BUILD_NUMBER}"
            }
        }
}
    post{
        always{
            sh "docker logout"
            }
       }
}