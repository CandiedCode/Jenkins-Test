#!/usr/bin/groovy
import groovy.json.JsonSlurper;

library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'candiedcode', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))

def urls = parseJsonResource('arsenalURLs.json')
def apps = parseJsonResource('ArsenalApps.json')
def branchEnvironment = mapBranchToEnvironments()

node {
	stage('checkout'){
			//checkout scm
			git credentialsId: 'candiedcode', url: 'git@github.com:logicnow/CheckProcessor.git', branch: 'feature/move_to_jenkins'

			sh "make build-image"
		}
	stage('run unit tests'){
		echo apps.apps
	}
}

stage('output'){
	def output = blueOrGreen(urls, env.BRANCH_NAME)
}