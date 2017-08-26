#!/usr/bin/groovy

stage('Library') {
    library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'github_ssh', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))
    }
}

//def urls = parseJsonResource("arsenalURLs.json")
//def apps = parseJsonResource("ArsenalApps.json")
//def branchEnvironment = mapBranchToEnvironments()

node {
	stage('blah'){
		checkout scm
	}
}

stage('Blue Or Green'){
	echo "test" //blueOrGreen(urls, 'testing')
}