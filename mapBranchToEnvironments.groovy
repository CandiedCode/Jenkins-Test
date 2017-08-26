#!/usr/bin/groovy

def call(){
	def environment = "test"

	/*switch (env.BRANCH_NAME) {	
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
			[]
			break
	}*/

	environment
}