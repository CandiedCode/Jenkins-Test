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
	stage('checkout checkprocessor'){
		//checkout scm
		git credentialsId: 'github_ssh', url: 'git@github.com:logicnow/CheckProcessor.git', branch: 'feature/move_to_jenkins'
	}

	stage('run unit tests'){
		withEnv(["BRANCH_NAME=${env.BRANCH_NAME}"]){
			sh "echo make build-image"
		}
	}

	//running all the unit tests in 1 parallel batch crashes jenkins
	stage('unit test - batch1'){
		parallel (
			checkNotifier: {sh "echo APPNAME=check-service-notifier make test"},
			deviceNotifier: {sh "echo APPNAME=device-status-consumer make test"},
			kinesisConsumer: {sh "echo APPNAME=kinesis-check-consumer make test"}
		)
   }

	stage('unit test - batch2'){
		parallel (
			legacyConsumer: {sh "echo APPNAME=legacy-uploads-consumer make test"},
			outagesConsumer: {sh "echo APPNAME=outages-consumer make test"},
			resultConsumer: {sh "echo APPNAME=results-consumer make test"}
		)
	}
    
    stage('unit test - batch3'){
        parallel (
          searchConsumer: {sh "echo APPNAME=search-consumer make test"},
          webConsumer: {sh "echo APPNAME=webhook-notifier make test"},
          api: {sh "echo APPNAME=api make test"}
        )
    }

	stage('Clean') {
		sh "echo make logout"
    	sh "echo make clean"
    }
}

node {
	stage('ts-provisioning checkout'){
		git credentialsId: 'github_ssh', url: 'git@github.com:logicnow/ts-provisioning.git', branch: 'master'

		stash "ts-provisioning-${env.BRANCH_NAME}"
	}
}

def branches = [:]
environments.each {
	branches["${it}"] = {
		node {
			stage('build infrastructure image'){
				unstash "ts-provisioning-${env.BRANCH_NAME}"
				
				currentColor = currentblueOrGreen(urlMap, it)
				buildColor = buildBlueGreen(currentColor)

				echo currentColor
				echo buildColor
			}

			dir("ansible/Makefiles") {
				def buildEnvironment = ${it}-${buildColor}
	            stage('Create Infrastructure') {
	                sh "echo make --file Makefile.arsenal build"
	                sh "echo make --file Makefile.arsenal base_layer BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	                sh "echo make --file Makefile.arsenal network_layer BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	                sh "echo make --file Makefile.arsenal elk_layer BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	                sh "echo make --file Makefile.arsenal eb_layer BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	                sh "echo make --file Makefile.arsenal monitoring_layer BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	                sh "echo make --file Makefile.arsenal rabbit_deploy BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	                sh "echo make --file Makefile.arsenal monitoring_layer BUILD_ENVIRONMENT=${buildEnvironment} ACTION=create"
	            }
			}
		}
	}
}
	
parallel branches	

