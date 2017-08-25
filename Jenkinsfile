#!/usr/bin/groovy
library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'candiedcode', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))



//def utils = new Utils()
def urls = libraryResource 'arsenalURLs.json'

stage('echo'){
	echo env.Branch_Name
}
stage('output'){
	def output = blueOrGreen()
}