pipeline {
    agent any
    options { disableConcurrentBuilds() }
    stages {
        stage('sleep'){
            steps {
                //echo BRANCH_NAME
                sh "echo $BRANCH_NAME"
                sleep 5
            }
        } 
        when {
            BRANCH != 'master' 
            
            stage('Workspace'){
                steps {
                    echo "this is not master"
                }
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
