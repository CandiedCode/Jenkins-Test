#!/usr/bin/groovy
library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'candiedcode', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))



//def utils = new Utils()


stage('echo'){
	echo env.Branch_Name
	echo library('jenkinstestlib').com.solarwinds.Utils.isBlue(env.BRANCH_NAME)
//		echo utils.isGreen(env.BRANCH_NAME)
}