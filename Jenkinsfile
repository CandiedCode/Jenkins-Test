#!/usr/bin/groovy
library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'candiedcode', repoOwner: 'candiedcode', repository: 'Jenkins-Test'))
import com.solarwinds.Utils


//def utils = new Utils()


stage('echo'){
	echo env.Branch_Name
//		echo utils.isBlue(env.BRANCH_NAME)
//		echo utils.isGreen(env.BRANCH_NAME)
	}