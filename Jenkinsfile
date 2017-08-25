#!/usr/bin/groovy
import com.solarwinds.Utils

def utils = new Utils()


stage('echo'){
		echo utils.isBlue(env.BRANCH_NAME)
		echo utils.isGreen(env.BRANCH_NAME)
	}