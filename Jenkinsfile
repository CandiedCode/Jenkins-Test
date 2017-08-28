#!/usr/bin/groovy

stage('setup'){
	//load shared library
	library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'github_ssh', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))

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
		withCredentials([usernamePassword(credentialsId: dockerhub_candiedcode, usernameVariable: 'username', passwordVariable: 'password')]) {
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
		sh "make logout"
    	sh "make clean"
    }
}