pipeline {
    agent any
    options { disableConcurrentBuilds() }
    stages {
        stage('sleep'){
            steps {
                echo BRANCH_NAME
                sleep 30
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
