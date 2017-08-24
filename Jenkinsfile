node {
	if (env.BRANCH_NAME == 'master') {
		stage('Master Example') {
			echo 'I only execute on the master branch'
		}
	} else {
	   stage('Not Master Example') {
			echo 'I only execute on not master branch'
		}
	}
}