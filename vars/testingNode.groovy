#!/usr/bin/groovy
def branches = ['develop', 'staging', 'master'];

def call(Map parameters = [:], body) {
	if (!branches.contains(env.BRANCH_NAME)){
		node {
			body()
		}
	}
	else {
		echo "branch isn't develop, staging, or master"
	}
}