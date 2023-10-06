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
                sh "docker build -t=aserbest/selenium ."  //aserbest dockerhub account username

            }
        }
         stage('push image'){
            steps{
                sh "docker push aserbest/selenium"
            }
        }
}

}