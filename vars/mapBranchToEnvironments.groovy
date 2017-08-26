#!/usr/bin/groovy

def call(){
	def environment

	switch (env.BRANCH_NAME) {	
		case: "develop":
			environment = ["testing"]
			break
		case: "staging"
			environment = ["staging"]
			break
		case: "master"
			environment = ["apac", "us", "emea"]
			break
		default:
			null
	}

	environment
}