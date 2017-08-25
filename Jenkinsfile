def utils = new com.solarwinds.Utils()

node {
	if (utils.branchMapToEnvironment.keySet().contains(env.BRANCH_NAME){
		stage('Master Example') {
			echo utils.isBlue(env.BRANCH_NAME)
			echo utils.isGreen(env.BRANCH_NAME)
		}
	} else {
		stage('blah branch') {
			echo blah
		}

	}
}