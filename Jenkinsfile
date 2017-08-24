pipeline {
    agent any
    options { disableConcurrentBuilds() }
    stages {
        stage('Library') {
            steps {
                library identifier: "jenkinstest@master", retriever: modernSCM(github(credentialsId: 'github_ssh', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))
            }
        }
        stage('sleep'){
            steps {
                //echo BRANCH_NAME
                sh "echo $BRANCH_NAME"
                sleep 5
            }
        }        
        stage('Workspace'){
            steps {
                echo env.WORKSPACE
                echo pwd()
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
