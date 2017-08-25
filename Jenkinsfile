#!/usr/bin/groovy
import com.solarwinds.Utils

library identifier: "jenkinstestlib@${env.BRANCH_NAME}", retriever: modernSCM(github(credentialsId: 'candiedcode', repoOwner: 'candiedcode', repository: 'Jenkins-Test')) _


def utils = new Utils()


stage('echo'){
		echo utils.isBlue(env.BRANCH_NAME)
		echo utils.isGreen(env.BRANCH_NAME)
	}