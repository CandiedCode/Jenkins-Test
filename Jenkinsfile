#!/usr/bin/groovy
library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'github_ssh', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))

def urls = libraryResource "arsenalURLs.json"
def arsenalApps = libraryResource "ArsenalApps.json"
def branchEnvironment = mapBranchToEnvironments()

stage('echo'){
	echo urls
	echo arsenalApps
	echo branchEnvironment()
}

node {
	stage('checkout'){
		//checkout scm
		git credentialsId: 'github_ssh', url: 'git@github.com:logicnow/CheckProcessor.git', branch: 'feature/move_to_jenkins'

		sh "echo make build-image"
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

	stage('Clean') {
    	sh "make clean"
    }
}

//def branchEnvironment = mapBranchToEnvironments()

node {
	stage('blah'){
		checkout scm
	}
}

stage('Blue Or Green'){
	echo "test" //blueOrGreen(urls, 'testing')
}
*/