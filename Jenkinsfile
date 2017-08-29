#!/usr/bin/groovy
properties([
	buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '12')), disableConcurrentBuilds(), 
	parameters([booleanParam(defaultValue: true, description: 'true or false', name: 'Test')])])

stage('setup'){
    echo "Hello ${params.Test}"
	//load shared library
	library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'github_ssh', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))

    env.BRANCH_NAME = "master"
	//load resources
	def urls = libraryResource "arsenalURLs.json"
	def arsenalApps = libraryResource "ArsenalApps.json"
	urlMap = parseJsonResource(urls)
	arsenalAppsMap = parseJsonResource(arsenalApps)

	//get environments for corresponding branch
	environments = mapBranchToEnvironments()
}

node {
	stage('checkout'){
		//checkout scm
		git credentialsId: 'github_ssh', url: 'git@github.com:logicnow/CheckProcessor.git', branch: 'feature/move_to_jenkins'
		
		withEnv(["BRANCH_NAME=${env.BRANCH_NAME}"]){
			sh "echo make build-image"
		}
	}

	stage('run unit tests'){
        sh "echo APPNAME=check-service-notifier make test"
        sh "echo APPNAME=device-status-consumer make test"
        sh "echo APPNAME=kinesis-check-consumer make test"
        sh "echo APPNAME=legacy-uploads-consumer make test"
        sh "echo APPNAME=outages-consumer make test"
        sh "echo APPNAME=results-consumer make test"
        sh "echo APPNAME=search-consumer make test"
        sh "echo APPNAME=webhook-notifier make test"
        sh "echo APPNAME=api make test"
	}

	if (environments.size() > 0) {
        withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'password', usernameVariable: 'username')]) {	    
			sh "echo make login DOCKERLOGIN=${username} DOCKERPASSWORD=${password}"
		}

		stage('build and deploy'){
	        sh "echo APPNAME=check-service-notifier make build-package"
	        sh "echo APPNAME=device-status-consumer make build-package"
	        sh "echo APPNAME=kinesis-check-consumer make build-package"
	        sh "echo APPNAME=legacy-uploads-consumer make build-package"
	        sh "echo APPNAME=outages-consumer make build-package"
	        sh "echo APPNAME=results-consumer make build-package"
	        sh "echo APPNAME=search-consumer make build-package"
	        sh "echo APPNAME=webhook-notifier make build-package"
	        sh "echo APPNAME=api make build-package"
		}
	}

	stage('Clean') {
		sh "echo make logout"
    	sh "echo make clean"
    }
}

environments.each {
	stage("test-${it}"){
		echo it
		echo blueOrGreen(urlMap, it)
	}
}

def branches = [:]
environments.each {
 	branches["${it}-Create Infrastructure"] = {
 		node {
 			stage("test-${it}"){
				echo it
				echo blueOrGreen(urlMap, it)
			}

			stage("test2-${it}"){
				echo it
				echo blueOrGreen(urlMap, it)
			}

			stage("test3-${it}"){
				echo it
				echo blueOrGreen(urlMap, it)
			}
 		}
 	}	
}
parallel branches